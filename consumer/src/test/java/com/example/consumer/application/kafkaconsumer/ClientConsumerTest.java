package com.example.consumer.application.kafkaconsumer;

import com.example.Address;
import com.example.Client;
import com.example.consumer.application.AbstractKafkaTest;
import com.example.consumer.controllers.ClientsController;
import com.example.consumer.doa.ClientsRepo;
import com.example.consumer.model.AddressEntity;
import com.example.consumer.model.ClientEntity;
import com.example.consumer.services.AddressService;
import com.example.consumer.services.ClientService;
import com.example.consumer.services.MySpecialKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import scala.reflect.api.Types;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientConsumerTest extends AbstractKafkaTest {

    @Autowired
    ClientsRepo clientsRepo;
    @Autowired
    AddressService addressService;
    @Autowired
    ClientService clientService;
    @Test
    public void get_client() {
        ClientEntity newClient = new ClientEntity();
        AddressEntity address = new AddressEntity();
        address.setAddressLine("DummyAddress");
        address.setPostCode("DummyPost");
        address.setState("DummyState");
        addressService.save(address);

        newClient.setAddressid(address);
        newClient.setFirstname("DummyName");
        newClient.setId("DummyId");
        newClient.setNetworth(100.0);
        newClient.setLastname("DummyLastName");

        clientService.save(newClient);
        Optional<ClientEntity> ce = clientsRepo.findById("DummyId");
        assertThat(ce.isPresent()).isTrue();
    }

}