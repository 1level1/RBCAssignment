package com.example.consumer.services;

import com.example.Client;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MySpecialKafkaConsumer {
    @Value("${client.topic}")
    private String topicName;

    @KafkaListener()
    public void consumeMsg(ConsumerRecord<String, Client> record) {

    }


}
