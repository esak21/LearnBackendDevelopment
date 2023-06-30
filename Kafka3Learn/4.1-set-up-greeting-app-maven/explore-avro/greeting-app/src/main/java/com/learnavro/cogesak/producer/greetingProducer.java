package com.learnavro.cogesak.producer;

import com.learnavro.cogesak.avroschemas.Greeting;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class greetingProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

         final String APP_TOPIC_NAME = "thirdparty_greeting";

        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());


        KafkaProducer<String, byte[]> producer = new KafkaProducer<String, byte[]>(prop);

        Greeting greeting = buildGreeting("Hello Fpm thirdparty data hub !! ");
        byte[] result;
        try {
            result = greeting.toByteBuffer().array();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProducerRecord<String, byte[]> producerrecord = new ProducerRecord<>(APP_TOPIC_NAME, result);

        var appMetaData = producer.send(producerrecord).get();
        System.out.println(appMetaData.toString());
    }

    private static Greeting buildGreeting(String s) {

        return Greeting.newBuilder()
                .setGreeting(s)
                .build();
    }
}
