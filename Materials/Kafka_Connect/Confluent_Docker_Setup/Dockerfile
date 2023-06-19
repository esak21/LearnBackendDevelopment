FROM confluentinc/cp-kafka-connect-base:7.3.0

ENV CONNECT_PLUGIN_PATH: "/usr/share/java,/usr/share/confluent-hub-components"

RUN confluent-hub install --no-prompt jcustenborder/kafka-connect-spooldir:2.0.65
RUN confluent-hub install --no-prompt neo4j/kafka-connect-neo4j:2.0.2
RUN confluent-hub install --no-prompt jcustenborder/kafka-connect-spooldir:2.0.43
