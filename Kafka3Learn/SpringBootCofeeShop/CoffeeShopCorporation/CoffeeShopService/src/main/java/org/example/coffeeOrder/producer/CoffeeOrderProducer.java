package org.example.coffeeOrder.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.coffeeshop.domain.generated.CoffeeOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CoffeeOrderProducer {

    @Value("${spring.kafka.topic}")
    private String APPTOPICNAME;
    private KafkaTemplate<String, CoffeeOrder> kafkaTemplate;

    public CoffeeOrderProducer(KafkaTemplate<String, CoffeeOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCoffeeOrderMessage (CoffeeOrder coffeeOrderAvro) {
        var CoffeeproducerRecord = new ProducerRecord<>(APPTOPICNAME , coffeeOrderAvro.getId().toString(), coffeeOrderAvro);

            var coffeeresults = kafkaTemplate.send(CoffeeproducerRecord);

        coffeeresults.whenComplete( (sendResult, throwable) -> {
            if(throwable != null) {
                handleFailure(coffeeOrderAvro, throwable);
            } else {
                handleSucess(coffeeOrderAvro, sendResult);
            }
        });



    }

    private void handleSucess(CoffeeOrder coffeeOrderAvro, SendResult<String, CoffeeOrder> coffeeresults) {

        log.info("MESSAGE SENT SUCESSFULLLY KEY --{} :: VALUE {} :: PARTITON {}", coffeeOrderAvro.getId(), coffeeOrderAvro, coffeeresults.getRecordMetadata().partition());

    }

    private void handleFailure(CoffeeOrder coffeeOrderAvro, Throwable throwable) {
        log.error("ERROR Sending Messages for  -- {} and the Exception is {}", coffeeOrderAvro, throwable.getMessage());
    }


}
