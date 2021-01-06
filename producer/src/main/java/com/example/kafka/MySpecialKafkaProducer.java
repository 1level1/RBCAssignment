package com.example.kafka;
import com.example.Address;
import com.example.Client;
import com.example.msgfacades.ClientMsg;
import org.apache.kafka.clients.producer.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class MySpecialKafkaProducer /* not so special ;-) */ implements IKafkaProducer {

    final static Logger logger = LogManager.getLogger(MySpecialKafkaProducer.class);

    private Producer<String, Client> producer;
    private String topic;

    public MySpecialKafkaProducer(Properties properties, String topic) {
        producer = new KafkaProducer<String, Client>(properties);
        this.topic = topic;
    }

    @Override
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
            ProducerRecord<String, Client> producerRecord = new ProducerRecord<String, Client>(topic, c);
            producer.send(producerRecord, (metadata, exception) -> {
                if (exception == null) {
                    logger.info(metadata);
                } else {
                    logger.error(exception.getMessage());
                }
            });
            producer.flush();
        } catch(Exception ex){
            return null;
        }
        return c;
    }
}
