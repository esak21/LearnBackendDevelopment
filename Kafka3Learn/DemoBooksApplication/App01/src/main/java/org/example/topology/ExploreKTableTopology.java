package org.example.topology;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;

public class ExploreKTableTopology {

    public static final String WORDS = "words";

    public static Topology build() {
//         Create the Builder
        StreamsBuilder streambuilder = new StreamsBuilder();

        var wordsTable = streambuilder.table(    WORDS,
                                Consumed.with(Serdes.String(), Serdes.String()),
                                Materialized.as("words-store")
        );

        wordsTable
                .filter( (key, value) ->  value.length() > 2 )
                .mapValues( value -> value.toUpperCase() )
                .toStream()
                .print(Printed.<String,String>toSysOut().withLabel("words-ktable"));

        return streambuilder.build();
    }
}
