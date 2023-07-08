package org.example.coffeeOrder.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.coffeeshop.domain.generated.CoffeeOrder;
import org.coffeeshop.domain.generated.CoffeeUpdateEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class CoffeeOrderUpdateProducer {

    @Value("${spring.kafka.topic}")
    private String APPTOPICNAME;
    private KafkaTemplate<String, CoffeeUpdateEvent> kafkaTemplate;

    public CoffeeOrderUpdateProducer(KafkaTemplate<String, CoffeeUpdateEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCoffeeOrderMessage (CoffeeUpdateEvent coffeeOrderAvro) {
        ProducerRecord<String, CoffeeUpdateEvent> CoffeeproducerRecord = new ProducerRecord<String, org.coffeeshop.domain.generated.CoffeeUpdateEvent>(APPTOPICNAME , coffeeOrderAvro.getId().toString(), coffeeOrderAvro);

        CompletableFuture<SendResult<String, CoffeeUpdateEvent>> coffeeresults = kafkaTemplate.send(CoffeeproducerRecord);

        coffeeresults.whenComplete( (sendResult, throwable) -> {
            if(throwable != null) {
                handleFailure(coffeeOrderAvro, throwable);
            } else {
                handleSucess(coffeeOrderAvro, sendResult);
            }
        });



    }

    private void handleSucess(CoffeeUpdateEvent coffeeOrderAvro, SendResult<String, CoffeeUpdateEvent> coffeeresults) {

        log.info("MESSAGE SENT SUCESSFULLLY KEY --{} :: VALUE {} :: PARTITON {}", coffeeOrderAvro.getId(), coffeeOrderAvro, coffeeresults.getRecordMetadata().partition());

    }

    private void handleFailure(CoffeeUpdateEvent coffeeOrderAvro, Throwable throwable) {
        log.error("ERROR Sending Messages for  -- {} and the Exception is {}", coffeeOrderAvro, throwable.getMessage());
    }


}
