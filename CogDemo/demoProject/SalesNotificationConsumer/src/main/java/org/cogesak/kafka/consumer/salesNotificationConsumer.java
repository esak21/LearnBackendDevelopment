package org.cogesak.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.cogesak.kafka.utils.slackService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class salesNotificationConsumer {

    private final slackService slackService;

    public salesNotificationConsumer(org.cogesak.kafka.utils.slackService slackService) {
        this.slackService = slackService;
    }

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord<String, GenericRecord> consumerrecord) {
        log.info("INFO :: we have received the record {} ={} ", consumerrecord.key(), consumerrecord.value());
//        Check if Offer discount is greater than 50%
         var offerPercentage = consumerrecord.value().get("offer").toString().replace('%',' ').trim();
         if ( Double.parseDouble(offerPercentage) >= 50.00 ) {
                log.info("INFO  :: OFFER PERCENTAGE IS {} -{}" , offerPercentage);
                log.info("Sending the Message to Notification ");
             slackService.sendMessageToSlack(consumerrecord.value().toString());
            }

    }

}
