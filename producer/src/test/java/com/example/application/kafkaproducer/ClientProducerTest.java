package com.example.application.kafkaproducer;

import com.example.Address;
import com.example.Client;
import com.example.application.AbstractKafkaTest;
import com.example.kafka.MyImprovedKafkaProducer;
import com.example.msgfacades.ClientMsg;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientProducerTest extends AbstractKafkaTest {

    @Autowired
    private MyImprovedKafkaProducer messageProducer;

    public final static ClientMsg testClientMsg = new ClientMsg("DummyAddress",
            "DummyCity","DummyState","DummyPost","DummyId",
            "DummyName",100.0,"DummyLastName");

    public final static Client refClient = Client.newBuilder()
            .setAddress(Address.newBuilder()
                    .setAddressline("DummyAddress")
                    .setCity("DummyCity")
                    .setPostcode("DummyPost")
                    .setState("DummyState")
                    .build())
            .setFirstname("DummyName")
            .setId("DummyId")
            .setNetworth(100)
            .setLastname("DummyLastName")
            .build();

    @Test
    public void send_client() {
        messageProducer.clientMsg(testClientMsg);

        ConsumerRecord<String, Client> singleRecord = KafkaTestUtils.getSingleRecord(clientConsumer, topic);
        assertThat(singleRecord).isNotNull();
    }

}