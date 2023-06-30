package org.coffeeshop;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.coffeeshop.domain.generated.CoffeeOrder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class coffeeConsumer {
    public static final String APP_TOPIC_NAME = "thirdparty_coffee";

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
                    CoffeeOrder coffee = decodeAvroGreeting(rec.value());
                    System.out.println(coffee.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        }

    }

    private static CoffeeOrder decodeAvroGreeting(byte[] value) throws IOException {
        return CoffeeOrder.fromByteBuffer(ByteBuffer.wrap(value));
    }


}
