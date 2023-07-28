package org.example.serdes;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import org.example.Domain.Order;
import org.example.Domain.Revenue;
import org.example.Domain.Store;
import org.example.Domain.TotalRevenue;

public class SerdesFactory {



    static public Serde<Order> orderSerde() {
        JsonSerializer<Order> jsonSerializer = new JsonSerializer<>();
        JsonDeserializer<Order> jsonDeserializer = new JsonDeserializer<>(Order.class);

        return Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    }

    static public Serde<Revenue> revenueSerde() {
        JsonSerializer<Revenue> jsonSerializer = new JsonSerializer<>();
        JsonDeserializer<Revenue> jsonDeserializer = new JsonDeserializer<>(Revenue.class);

        return Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    }

    public static Serde<TotalRevenue> totalRevenueSerdes() {

        JsonSerializer<TotalRevenue> jsonSerializer = new JsonSerializer<>();
        JsonDeserializer<TotalRevenue> jsonDeserializer = new JsonDeserializer<>(TotalRevenue.class);

        return Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    }

    public static Serde<Store> storeserdes() {

        JsonSerializer<Store> jsonSerializer = new JsonSerializer<>();
        JsonDeserializer<Store> jsonDeserializer = new JsonDeserializer<>(Store.class);

        return Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    }
}
