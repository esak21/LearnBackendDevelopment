package org.example.topology;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.example.Domain.Greeting;
import org.example.serdes.SerdesFactory;

public class GreetingsTopology {

    public static String GREETINGS = "greetings";

    public static String GREETINGS_UPPER = "greetings_uppercase";

    public static Topology buildTopology() {

        StreamsBuilder streamsBuilder  = new StreamsBuilder();

        var greetingsStream = getCustomGreetingKStream(streamsBuilder);

//        Prints the Stream to Console
        greetingsStream.print(Printed.<String, Greeting>toSysOut().withLabel("greetingsStream"));

        KStream<String, Greeting> modifiedStream = greetingsStream.mapValues(
                (readOnlyKey, value) -> new Greeting(value.message().toUpperCase() , value.timestamp() )

        );

        //        Prints the Stream to Console
        modifiedStream.print(Printed.<String, Greeting>toSysOut().withLabel("modifiedStream"));



//        Add Sink Processor
        modifiedStream.to(
                GREETINGS_UPPER,
                Produced.with(Serdes.String(), SerdesFactory.greetingSerdesUsingGenerics())
        );



//        Return the Topology
        return streamsBuilder.build();
    }

    private static KStream<String, Greeting> getCustomGreetingKStream(StreamsBuilder streamsBuilder) {
        KStream<String, Greeting> greetingStream = streamsBuilder
                .stream(GREETINGS, Consumed.with(Serdes.String(), SerdesFactory.greetingSerdesUsingGenerics()) );


        return greetingStream;

    }
}
