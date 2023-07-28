package org.example.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
public class ProducerUtil {

    static KafkaProducer<String, String> kafkaproducer = new KafkaProducer<String, String>(producerprop());

    private static Map<String, Object> producerprop() {
        Map<String, Object> prop = new HashMap<>();

        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return prop;
    }

    public static RecordMetadata publishMessage(String topicName, String key, String Message) {

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, key, Message);
        RecordMetadata recordMetadata = null;

        try {
            recordMetadata = kafkaproducer.send(producerRecord).get();
        } catch (InterruptedException e) {
            log.error("InterruptedException in  publishMessageSync : {}  ", e.getMessage(), e);
        } catch (ExecutionException e) {
            log.error("ExecutionException in  publishMessageSync : {}  ", e.getMessage(), e);
        }catch(Exception e){
            log.error("Exception in  publishMessageSync : {}  ", e.getMessage(), e);
        }
        return recordMetadata;
    }

}
