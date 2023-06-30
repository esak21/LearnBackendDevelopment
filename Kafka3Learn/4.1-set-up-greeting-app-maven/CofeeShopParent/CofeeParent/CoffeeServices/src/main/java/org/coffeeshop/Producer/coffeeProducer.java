package org.coffeeshop.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.coffeeshop.domain.generated.CoffeeOrder;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static org.coffeeshop.util.CofeeOrderUtil.buildnewCoffeeOrder;

public class coffeeProducer {

    private static final String APP_TOPIC_NAME = "thirdparty_coffee";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());

        KafkaProducer<String, byte[]> producer = new KafkaProducer<String, byte[]>(prop);

        CoffeeOrder coffeeorder = buildnewCoffeeOrder();
        System.out.println(coffeeorder.toString());

        byte[] result = coffeeorder.toByteBuffer().array();

        ProducerRecord<String, byte[]> producerrecord = new ProducerRecord<String, byte[]> (APP_TOPIC_NAME, result);
        var appmetadata = producer.send(producerrecord).get();

        System.out.println("Metadata is " + appmetadata.toString());




    }

}
