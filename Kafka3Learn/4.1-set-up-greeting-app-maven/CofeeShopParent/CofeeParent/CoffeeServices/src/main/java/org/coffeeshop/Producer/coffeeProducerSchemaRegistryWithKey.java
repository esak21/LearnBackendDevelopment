package org.coffeeshop.Producer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.coffeeshop.domain.generated.CoffeeOrder;
import org.coffeeshop.domain.generated.OrderId;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static org.coffeeshop.util.CofeeOrderUtil.buildnewCoffeeOrder;

public class coffeeProducerSchemaRegistryWithKey {

    private static final String APP_TOPIC_NAME = "thirdparty_coffee-rec";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        prop.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        KafkaProducer<OrderId, CoffeeOrder> producer = new KafkaProducer<OrderId, CoffeeOrder>(prop);

        CoffeeOrder coffeeorder = buildnewCoffeeOrder();
        System.out.println(coffeeorder.toString());

//        byte[] result = coffeeorder.toByteBuffer().array();

        ProducerRecord<OrderId, CoffeeOrder> producerrecord = new ProducerRecord<OrderId, CoffeeOrder> (APP_TOPIC_NAME, coffeeorder.getId(), coffeeorder);
        var appmetadata = producer.send(producerrecord).get();

        System.out.println("Metadata is " + appmetadata.toString());




    }

}
