CREATE DATABASE sales;

psql -d sales 

CREATE TABLE EMPLOYEE_TXS
(
	TXN_ID integer	 NOT NULL,
	SENDER_NM VARCHAR ( 150 ) NOT NULL, 
	RECEIVER_NM VARCHAR ( 150 ) NOT NULL, 
	SENDER_ACCT_NM VARCHAR ( 50 ) NOT NULL,
	RECEIVER_ACCT_NM VARCHAR ( 50 ) NOT NULL,
	AMNT integer, 
	SENDER_BNK VARCHAR ( 150 ) NOT NULL, 
	TXN_DT TIMESTAMP
	
);


INSERT INTO EMPLOYEE_TXS (TXN_ID ,SENDER_NM , RECEIVER_NM , SENDER_ACCT_NM ,RECEIVER_ACCT_NM ,AMNT , SENDER_BNK , TXN_DT )
VALUES ( 1, 'CMPNY', 'ESAKKI', 'CMPAX122', 'ESAK123v1', 7843, 'RBI', NOW() )
INSERT INTO EMPLOYEE_TXS (TXN_ID ,SENDER_NM , RECEIVER_NM , SENDER_ACCT_NM ,RECEIVER_ACCT_NM ,AMNT , SENDER_BNK , TXN_DT )
VALUES ( 2, 'CMPNY2', 'ESAKKI2', 'CMPAX122', 'ESAK223v1', 843, 'RBI', NOW() )
INSERT INTO EMPLOYEE_TXS (TXN_ID ,SENDER_NM , RECEIVER_NM , SENDER_ACCT_NM ,RECEIVER_ACCT_NM ,AMNT , SENDER_BNK , TXN_DT )
VALUES ( 3, 'CMPNY3', 'ESAKKI3', 'CMPAX122', 'ESAK323v1', 743, 'RBI', NOW() )
INSERT INTO EMPLOYEE_TXS (TXN_ID ,SENDER_NM , RECEIVER_NM , SENDER_ACCT_NM ,RECEIVER_ACCT_NM ,AMNT , SENDER_BNK , TXN_DT )
VALUES ( 4, 'CMPNY4', 'ESAKKI4', 'CMPAX122', 'ESAK423v1', 78, 'RBI', NOW() )
INSERT INTO EMPLOYEE_TXS (TXN_ID ,SENDER_NM , RECEIVER_NM , SENDER_ACCT_NM ,RECEIVER_ACCT_NM ,AMNT , SENDER_BNK , TXN_DT )
VALUES ( 5, 'CMPNY5', 'ESAKKI5', 'CMPAX122', 'ESAK523v1', 7843, 'RBI', NOW() )


"connector.class": "io.debezium.connector.postgresql.PostgresConnector",
"tasks.max": "1",
"database.hostname": "postgres",
"database.port": "5432",
"database.user": "postgres",
"database.password": "postgres",
"database.dbname": "sales",
"database.server.name": "ARCTYPE",
"key.converter": "org.apache.kafka.connect.json.JsonConverter",
"value.converter": "org.apache.kafka.connect.json.JsonConverter",
"key.converter.schemas.enable": "false",
"value.converter.schemas.enable": "false",
"snapshot.mode": "always"


{
  "name": "PostgresConnector",
  "tasks.max": 1,
   "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
    "plugin.name": "pgoutput",
    "database.hostname": "postgres",
    "database.port": "5432",
    "database.user": "postgres",
    "database.password": "postgres",
    "database.dbname" : "postgres",
    "database.server.name": "postgres",
    "table.include.list": "sales.(.*)",
    "heartbeat.interval.ms": "5000",
    "slot.name": "dbname_debezium",
    "publication.name": "dbname_publication",
    "transforms": "AddPrefix",
    "transforms.AddPrefix.type": "org.apache.kafka.connect.transforms.RegexRouter",
    "transforms.AddPrefix.regex": "pg-dev.sales.(.*)",
    "transforms.AddPrefix.replacement": "data.cdc.dbname", 
    "topic.prefix" : "esak"
     
 }
 
        "connector.class": "io.confluent.connect.jdbc.JdbcSourceConnector",
        "connection.url": "jdbc:postgresql://172.26.0.4:5432/postgres",
        "connection.user": "postgres",
        "connection.password": "postgres",
        "dialect.name": "PostgreSqlDatabaseDialect",
        "topic.prefix": "myapp-01-",
        "mode": "bulk",
        "auto.evolve": true,
        "auto.create": true,
        "table_type": "TABLE",
        "table.whitelist": "EMPLOYEE_TXS",
        "validate.non.null": false,
        "transforms": "InsertField, insertTS",
        "transforms.InsertField.type": "org.apache.kafka.connect.transforms.InsertField$Value",
        "transforms.InsertField.static.field": "MessageSource",
        "transforms.InsertField.static.value": "PSQL",
        "transforms.insertTS.type": "org.apache.kafka.connect.transforms.InsertField$Value",
        "transforms.insertTS.timestamp.field": "load_dt"
        
        
        
        
        
        
        
        
        
        
{

        "connector.class": "io.confluent.connect.jdbc.JdbcSourceConnector",
        "connection.url": "jdbc:postgresql://172.26.0.4:5432/postgres",
        "connection.user": "postgres",
        "connection.password": "postgres",
        "dialect.name": "PostgreSqlDatabaseDialect",
        "topic.prefix": "myapp-01-",
        "poll.interval.ms" : 3600,
        "mode":"bulk"
}

