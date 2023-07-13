package org.example.topology;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;

public class GreetingsTopology {

    public static String GREETINGS = "greetings";

    public static String GREETINGS_UPPER = "greetings_uppercase";

    public static Topology buildTopology() {

        StreamsBuilder streamsBuilder  = new StreamsBuilder();

//         Add Source Processor
        var greetingsStream = streamsBuilder.
                stream(GREETINGS, Consumed.with(Serdes.String(), Serdes.String()));

//        Prints the Stream to Console
        greetingsStream.print(Printed.<String, String>toSysOut().withLabel("greetingsStream"));
        //Add Processing Logic
        var modifiedStream = greetingsStream.mapValues( (readonlyKey, value) -> value.toUpperCase());

        //        Prints the Stream to Console
        modifiedStream.print(Printed.<String, String>toSysOut().withLabel("modifiedStream"));


//        Add Sink Processor
        modifiedStream.to(
                GREETINGS_UPPER,
                Produced.with(Serdes.String(), Serdes.String())
        );



//        Return the Topology
        return streamsBuilder.build();
    }
}
