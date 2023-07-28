package org.example.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;

@Slf4j
public class JsonDeserializer<T> implements Deserializer<T> {

    private Class<T> destinationClass;

    public JsonDeserializer(Class<T> destinationClass) {
        this.destinationClass = destinationClass;
    }

    private  final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule( new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    @Override
    public T deserialize(String s, byte[] data) {

        if (data == null ){
            return null;
        }

        try {
            return objectMapper.readValue(data, destinationClass);
        }  catch (IOException e) {
            log.error("IO Exception Occured in  Deserialization  - {}", e.getMessage());
            throw new RuntimeException(e);
        }catch (Exception e) {
            log.error("Exception Occured in Deserialization - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
