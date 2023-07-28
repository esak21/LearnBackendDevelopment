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

public class GreetingsTopologyOlder {

    public static String GREETINGS = "greetings";

    public static String GREETINGS_UPPER = "greetings_uppercase";

    public static Topology buildTopology() {

        StreamsBuilder streamsBuilder  = new StreamsBuilder();

//         Add Source Processor
//        var greetingsStream = streamsBuilder.
//                stream(GREETINGS, Consumed.with(Serdes.String(), Serdes.String()));

        var greetingsStream = getCustomGreetingKStream(streamsBuilder);

//        Prints the Stream to Console
        greetingsStream.print(Printed.<String, Greeting>toSysOut().withLabel("greetingsStream"));

        //Add Processing Logic
//        var modifiedStream = greetingsStream
//                .filter( (key,value) -> value.message().length() > 5 )
//                .peek((k,v) -> {
//                    System.out.println("After Filter : key is " +k + "value is" + v );
//                })
//        .mapValues( (readonlyKey, value) -> value.toUpperCase());

        KStream<String, Greeting> modifiedStream = greetingsStream.mapValues(
                (readOnlyKey, value) -> new Greeting(value.message().toUpperCase() , value.timestamp() )

        );

        //        Prints the Stream to Console
        modifiedStream.print(Printed.<String, Greeting>toSysOut().withLabel("modifiedStream"));


//        Add Sink Processor
        modifiedStream.to(
                GREETINGS_UPPER,
                Produced.with(Serdes.String(), SerdesFactory.greetingSerdes())
        );



//        Return the Topology
        return streamsBuilder.build();
    }

    private static KStream<String, Greeting> getCustomGreetingKStream(StreamsBuilder streamsBuilder) {
        KStream<String, Greeting> greetingStream = streamsBuilder
                .stream(GREETINGS, Consumed.with(Serdes.String(), SerdesFactory.greetingSerdes()) );


        return greetingStream;

    }
}
