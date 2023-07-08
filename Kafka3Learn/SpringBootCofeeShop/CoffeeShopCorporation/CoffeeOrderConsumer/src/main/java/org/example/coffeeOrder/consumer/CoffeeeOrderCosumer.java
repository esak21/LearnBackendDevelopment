package org.example.coffeeOrder.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.coffeeshop.domain.generated.CoffeeOrder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CoffeeeOrderCosumer {

//    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
//    public void onMessage(ConsumerRecord<String, CoffeeOrder> consumerrecord) {
//        log.info("INFO we have received the record  {} - {}", consumerrecord.key(), consumerrecord.value());
//    }

//     To make it as More Specific
    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord<String, GenericRecord> consumerrecord) {
        log.info("INFO we have received the record  {} - {}", consumerrecord.key(), consumerrecord.value());
    }
}
