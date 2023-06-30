package com.learnavro.cogesak.consumer;

import com.learnavro.cogesak.avroschemas.Greeting;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class greetingsConsumer {
    private static final String APP_TOPIC_NAME = "thirdparty_greeting";

    public static void main(String[] args) {

        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "greeting.Consumer");
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,  "earliest");


        KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<String, byte[]>(prop);
        consumer.subscribe(Collections.singletonList(APP_TOPIC_NAME));

        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));

            for(ConsumerRecord<String, byte[]> rec: records) {
                try {
                    Greeting greeting = decodeAvroGreeting(rec.value());
                    System.out.println(greeting.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        }
    }

    private static Greeting decodeAvroGreeting(byte[] value) throws IOException {
            return Greeting.fromByteBuffer(ByteBuffer.wrap(value));
    }


}
