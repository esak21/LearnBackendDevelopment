package org.example.launcher;




import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.example.topology.GreetingsTopology;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


public class GreetingsStreamApp {

    public static void main(String[] args) {

        Properties prop = new Properties();
        prop.put(StreamsConfig.APPLICATION_ID_CONFIG, "greetings-App");
        prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092");
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        createTopics(prop, List.of(GreetingsTopology.GREETINGS, GreetingsTopology.GREETINGS_UPPER));

        var greetingsTopology = GreetingsTopology.buildTopology();

//         Create the Kafka Application
        var kafkaApp = new KafkaStreams(greetingsTopology, prop);
// GraceFully ShutDown the Appp
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaApp :: close));

        try{
            kafkaApp.start();
        } catch ( Exception e ) {

            System.out.println("Exception Occures " + e);
        }




    }

    private static void createTopics(Properties config, List<String> greetings) {

        AdminClient admin = AdminClient.create(config);
        var partitions = 1;
        short replication  = 1;

        var newTopics = greetings
                .stream()
                .map(topic ->{
                    return new NewTopic(topic, partitions, replication);
                })
                .collect(Collectors.toList());

        var createTopicResult = admin.createTopics(newTopics);
        try {
            createTopicResult
                    .all().get();
            System.out.println("topics are created successfully");
        } catch (Exception e) {
            System.out.println("Exception creating topics : {}  " + e.getMessage() + e);
        }
    }
}
