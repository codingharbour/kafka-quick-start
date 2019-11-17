package com.codingharbour.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Instant;
import java.util.Properties;

public class SimpleKafkaProducer {

    public static void main(String[] args) {
        //create kafka producer
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        Producer<String, String> producer = new KafkaProducer<>(properties);

        //prepare the record
        String recordValue = "Current time is " + Instant.now().toString();
        System.out.println("Sending message: " + recordValue);
        ProducerRecord<String, String> record = new ProducerRecord<>("java_topic", null, recordValue);

        //produce the record
        producer.send(record);
        producer.flush();

        //close the producer at the end
        producer.close();
    }
}
