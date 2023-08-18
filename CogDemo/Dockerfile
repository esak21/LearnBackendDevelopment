FROM cnfldemos/cp-server-connect-datagen:0.6.0-7.3.0

ENV CONNECT_PLUGIN_PATH: "/usr/share/java,/usr/share/confluent-hub-components"

RUN confluent-hub install --no-prompt confluentinc/kafka-connect-s3:10.5.1
RUN confluent-hub install --no-prompt confluentinc/kafka-connect-aws-lambda:2.0.6
RUN confluent-hub install --no-prompt confluentinc/kafka-connect-elasticsearch:14.0.6
RUN confluent-hub install --no-prompt confluentinc/kafka-connect-jdbc:10.7.3
