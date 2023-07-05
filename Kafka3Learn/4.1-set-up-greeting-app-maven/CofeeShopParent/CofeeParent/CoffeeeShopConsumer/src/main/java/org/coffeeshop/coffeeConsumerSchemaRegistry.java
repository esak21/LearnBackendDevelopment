package org.coffeeshop;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
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

public class coffeeConsumerSchemaRegistry {
    public static final String APP_TOPIC_NAME = "thirdparty_coffee-sr";

    public static void main(String[] args) {

        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "greeting.Consumer");
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,  "earliest");
        prop.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG,  "http://localhost:8081");
        prop.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG,  true);


        KafkaConsumer<String, CoffeeOrder> consumer = new KafkaConsumer<String, CoffeeOrder>(prop);
        consumer.subscribe(Collections.singletonList(APP_TOPIC_NAME));


        while (true) {
            ConsumerRecords<String, CoffeeOrder> records = consumer.poll(Duration.ofMillis(100));

            for(ConsumerRecord<String, CoffeeOrder> rec: records) {
                try {
//                    CoffeeOrder coffee = decodeAvroGreeting(rec.value());
                    System.out.println(rec.toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }


        }

    }

    private static CoffeeOrder decodeAvroGreeting(byte[] value) throws IOException {
        return CoffeeOrder.fromByteBuffer(ByteBuffer.wrap(value));
    }


}
