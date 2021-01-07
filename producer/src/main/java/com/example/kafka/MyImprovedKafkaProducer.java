package com.example.kafka;

import com.example.Address;
import com.example.Client;
import com.example.msgfacades.ClientMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyImprovedKafkaProducer implements IKafkaProducer {

    @Autowired
    private KafkaTemplate<String, Client> kafkaClientTemplate;

    @Value("${client.topic}")
    String topic;

    public Client clientMsg(ClientMsg client) {
        Client c = null;
        try {
            Address a = Address.newBuilder()
                    .setAddressline(client.getAddressline())
                    .setCity(client.getCity())
                    .setPostcode(client.getPostcode())
                    .setState(client.getState())
                    .build();
            c = Client.newBuilder()
                    .setAddress(a)
                    .setFirstname(client.getFirstname())
                    .setId(client.getId())
                    .setLastname(client.getLastname())
                    .setNetworth(client.getNetworth())
                    .build();
            kafkaClientTemplate.send(topic, c);
        } catch(Exception ex){
            return null;
        }
        return c;
    }

}
