package org.cogesak.kafka.Producer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.cogesak.domain.generated.Sales;
import org.cogesak.kafka.datagenerator.SalesGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SalesProducer {

    @Value("${spring.kafka.topic}")
    public String topicName;

    private final KafkaTemplate<Integer, Sales> kafkaTemplate;

    private final SalesGenerator salesGenerator;

    public SalesProducer(KafkaTemplate<Integer, Sales> kafkaTemplate, SalesGenerator salesGenerator) {
        this.kafkaTemplate = kafkaTemplate;
        this.salesGenerator = salesGenerator;
    }

    public void sendSalesAvroProducer(Sales salesAvroData) {
        log.info("INFO:: starting the Producer");

        var salesAvroRecord = new ProducerRecord<>(topicName, salesAvroData.getSalesid(), salesAvroData);
        var salesresults = kafkaTemplate.send(salesAvroRecord);
        salesresults.whenComplete( (sendResult, throwable) -> {
            if(throwable != null ){
                handleFailureRecords(salesAvroData, throwable);
            } else{
                handleSucessRecord(salesAvroData, sendResult);
            }
        });

    }

    private void handleSucessRecord(Sales salesAvroData, SendResult<Integer, Sales> sendResult) {
        log.info("INFO :: Message send Sucessfully - key => {} - value => {} Partition is {} -{} -{} ", salesAvroData.getSalesid(), salesAvroData, sendResult.getRecordMetadata().partition(), sendResult.getRecordMetadata().offset(), sendResult.getRecordMetadata().hasTimestamp());
    }

    private void handleFailureRecords(Sales salesAvroData, Throwable throwable) {
        log.info("Error Sending Message and the Exeption is --{} ", throwable.getMessage());
    }

}
