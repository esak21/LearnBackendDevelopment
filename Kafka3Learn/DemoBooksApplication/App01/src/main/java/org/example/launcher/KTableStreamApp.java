package org.example.launcher;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.example.topology.ExploreKTableTopology;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


import static org.example.topology.ExploreKTableTopology.WORDS;

@Slf4j
public class KTableStreamApp {

    public static void main(String[] args) {

        var KTableTopology = ExploreKTableTopology.build();

        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "ktable");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        createTopics(config, List.of(WORDS));

        var kafkastreams = new KafkaStreams(KTableTopology, config);

        Runtime.getRuntime().addShutdownHook(new Thread(kafkastreams::close));
        log.info("Application Started ------");
        kafkastreams.start();
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
