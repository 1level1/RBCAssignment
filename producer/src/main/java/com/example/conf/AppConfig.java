package com.example.conf;

import com.example.kafka.IKafkaProducer;
import com.example.kafka.MySpecialKafkaProducer;
import com.example.services.ClientService;
import com.example.services.IClientService;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class AppConfig {
    @Value("${bootstrap.url}")
    String bootstrap;

    @Value("${registry.url}")
    String registry;

    @Value("${client.topic}")
    String topic;

    @Bean
    public IKafkaProducer kafkaProducer() {
        Properties properties = new Properties();
        // Kafka Properties
        properties.setProperty("bootstrap.servers", bootstrap);
        properties.setProperty("acks", "all");
        properties.setProperty("retries", "10");
        // Avro properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", registry);
        return  new MySpecialKafkaProducer(properties, topic);
    }
}
