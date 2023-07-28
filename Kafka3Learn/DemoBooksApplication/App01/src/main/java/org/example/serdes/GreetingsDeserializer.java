package org.example.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.Domain.Greeting;

import java.io.IOException;

@Slf4j
public class GreetingsDeserializer implements Deserializer<Greeting> {

    private ObjectMapper objectMapper;

    public GreetingsDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Greeting deserialize(String s, byte[] bytesdata) {
        try {
          return   objectMapper.readValue(bytesdata, Greeting.class);
        } catch (IOException e) {
            log.error("IO Exception Occured - {}", e.getMessage());
            throw new RuntimeException(e);
        }catch (Exception e) {
            log.error("Exception Occured - {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
