package org.example.Topology;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.Stores;
import org.example.Domain.*;
import org.example.serdes.SerdesFactory;

@Slf4j
public class OrdersTopology {

    public static final String ORDERS = "orders";
    public static final String GENERALORDERS = "orders_general";
    public static final String RESTORDERS = "orders_rest";
    public static final String STORES  = "stores";
    public static final String GENERAL_ORDERS_REVENUE = "general_orders_revenue";
    public static final String RESTAURANT_ORDERS_REVENUE  = "restaurant_orders_revenue";

    public static final String GENERAL_ORDERS_COUNT  = "general_orders_count";
    public static final String RESTAURANT_ORDERS_COUNT  = "restaurant_orders_count";



    public static Topology buildTopology() {

        Predicate<String, Order> generalPredicate =     (key, order) -> order.orderType().equalsIgnoreCase("GENERAL");
        Predicate<String, Order> restaurantPredicate =     (key, order) -> order.orderType().equalsIgnoreCase("RESTAURANT");

        ValueMapper<Order, Revenue> revenueValueMapper = order -> new Revenue(order.locationId(), order.finalAmount());

        StreamsBuilder builder = new StreamsBuilder();

//        joinKstreamWithKTable(builder);

        KStream<String, Order> orderStream =
                builder.stream(
                    ORDERS,
                    Consumed.with(Serdes.String(), SerdesFactory.orderSerde())
                    );

        orderStream.print(Printed.<String, Order>toSysOut().withLabel("Orders"));

//        Creating the Store Table
        var StoresTable = builder.table(STORES,
                Consumed.with(Serdes.String(), SerdesFactory.storeserdes()));
        orderStream
                .split(Named.as("General-Rest-Streams"))
                .branch(generalPredicate,
                        Branched.withConsumer( genralorderstream -> {
                            genralorderstream.print(Printed.<String, Order>toSysOut().withLabel("GeneralStream"));
//                            genralorderstream.mapValues( (readonly, value) -> revenueValueMapper.apply(value)
//                            ).to(
//                                    GENERALORDERS,
//                                    Produced.with(Serdes.String(), SerdesFactory.revenueSerde())
//                            );

//                             Lets Start the Aggregation
                            aggregateOrdersByCount(genralorderstream, StoresTable, GENERAL_ORDERS_COUNT);
                            aggregateOrderByRevenue(genralorderstream, StoresTable,  GENERAL_ORDERS_REVENUE);
                        })


                        )
                .branch(
                        restaurantPredicate,
                        Branched.withConsumer(restaurantorderStream -> {
                            restaurantorderStream.print(Printed.<String, Order>toSysOut().withLabel("restrauntStream"));
//                            restaurantorderStream
//                                    .mapValues(
//                                        (key, value) -> revenueValueMapper.apply(value)
//                                    ).to(
//                                        RESTORDERS,
//                                        Produced.with(Serdes.String(), SerdesFactory.revenueSerde())
//                                    );

                        // performing Aggragation
                            aggregateOrdersByCount(restaurantorderStream, StoresTable, RESTAURANT_ORDERS_COUNT);
                            aggregateOrderByRevenue(restaurantorderStream, StoresTable, RESTAURANT_ORDERS_REVENUE);
                        })
                );



        return builder.build();
    }

//    private static void joinKstreamWithKTable(StreamsBuilder streambuilder) {
//
//        var albhabetsstream = streambuilder.stream("APLHABETS", Consumed.with(Serdes.String(), Serdes.String()));
//        var alphabetsTables = streambuilder.table("ALPHA", Consumed.with(Serdes.String(), Serdes.String()));
//
//
//        ValueJoiner<String, String, Alphabet> valueJoiner = Alphabet::new;
//
//        var joinedStream = albhabetsstream.join(alphabetsTables, valueJoiner);
//
//        joinedStream.print(Printed.<String,Alphabet>toSysOut().withLabel("JoinedAlphabets"));
//
//    }

    private static void aggregateOrderByRevenue(KStream<String, Order> genralorderstream, KTable<String, Store> StoresTable, String generalOrdersRevenue) {
// Creating the Intializer
       Initializer<TotalRevenue> orderAggreagateInitializer = TotalRevenue::new;

       // Create the Aggreagator

        Aggregator<String, Order, TotalRevenue> aggregator  = (key, value, aggregate) -> aggregate.updateRunningRevenue(key,value);

        // calling the Main Aggregate Function
        var revenueTable = genralorderstream
                .map( (k,v) -> KeyValue.pair(v.locationId(), v) )
                .groupByKey( Grouped.with(Serdes.String(), SerdesFactory.orderSerde()))
                .aggregate(orderAggreagateInitializer,
                        aggregator,
                        Materialized.<String, TotalRevenue, KeyValueStore<Bytes, byte[]>>as(generalOrdersRevenue).withKeySerde(Serdes.String()).withValueSerde(SerdesFactory.totalRevenueSerdes())

                );

//         Ktable to Ktable Join

        ValueJoiner<TotalRevenue, Store, TotalRevenueWithAddress> valuejoiner = TotalRevenueWithAddress::new;

        var revenueWithStoreTable = revenueTable.join(StoresTable, valuejoiner);

        revenueWithStoreTable.toStream()
                        .print(Printed.<String, TotalRevenueWithAddress>toSysOut().withLabel(generalOrdersRevenue));



        revenueTable.toStream().print(Printed.<String, TotalRevenue>toSysOut().withLabel(generalOrdersRevenue+"by-store"));
    }

    private static void aggregateOrdersByCount(KStream<String, Order> genralorderstream, KTable<String, Store> StoresTable, String generalOrdersCount) {

       var ordersCountPerStore =  genralorderstream
                .map((key, value) ->  KeyValue.pair(value.locationId(), value))
                .groupByKey( Grouped.with(Serdes.String(), SerdesFactory.orderSerde()))
                .count(Named.as(generalOrdersCount), Materialized.as(generalOrdersCount) );

       ordersCountPerStore.toStream().print(Printed.<String, Long>toSysOut().withLabel(generalOrdersCount));

        ValueJoiner<Long, Store, TotalCountWithAddress> valuejoiner = TotalCountWithAddress::new;

        var joinedCountStream = ordersCountPerStore.join(StoresTable,valuejoiner);

        joinedCountStream.toStream().print(Printed.<String, TotalCountWithAddress>toSysOut().withLabel(generalOrdersCount+"by-order-count"));
    }

}
