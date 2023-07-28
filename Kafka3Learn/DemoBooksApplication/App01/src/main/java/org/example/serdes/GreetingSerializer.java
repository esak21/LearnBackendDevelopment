package org.example.serdes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.example.Domain.Greeting;

import java.util.Map;

@Slf4j
public class GreetingSerializer implements Serializer<Greeting> {


    private ObjectMapper objectMapper ;

    public GreetingSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public byte[] serialize(String s, Greeting greeting) {
        try {
            return objectMapper.writeValueAsBytes(greeting);
        } catch (JsonProcessingException e) {
            log.error("Json Processing Error :: {} ", e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error(" Exception  Error :: {} ", e.getMessage());
            throw new RuntimeException(e);
        }


    }


}
