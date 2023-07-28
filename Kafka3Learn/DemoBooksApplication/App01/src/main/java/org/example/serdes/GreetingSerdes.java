package org.example.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.example.Domain.Greeting;

public class GreetingSerdes implements Serde<Greeting> {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule( new JavaTimeModule() )
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    @Override
    public Serializer<Greeting> serializer() {
        return new GreetingSerializer(objectMapper);
    }

    @Override
    public Deserializer<Greeting> deserializer() {
        return new GreetingsDeserializer(objectMapper);
    }
}
