package com.example.consumer.services;

import com.example.Address;
import com.example.Client;
import com.example.consumer.model.AddressEntity;
import com.example.consumer.model.ClientEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MySpecialKafkaConsumer {
    final static Logger logger = LogManager.getLogger(MySpecialKafkaConsumer.class);
    @Value("${client.topic}")
    String topic;

    @Bean
    public String topicName() {
        return topic;
    }

    @Value("${client.topic}")
    private String topicName;

    ClientService clientService;
    AddressService addressService;

    @Autowired
    public MySpecialKafkaConsumer(ClientService clientService,AddressService addressService) {
        this.clientService = clientService;
        this.addressService = addressService;
    }

    @KafkaListener(topics = "#{@topicName}")
    public void consumeMsg(ConsumerRecord<String, Client> record) {
        Client c = record.value();
        logger.info("Recieved record with client: "+c.toString());
        Optional<ClientEntity> ce = clientService.findById(c.getId());
        if (ce.isPresent()) {
            this.updateClient(ce.get(),c);
        } else {
            Address address = c.getAddress();
            ClientEntity newClient = new ClientEntity();
            AddressEntity updatedAddress = updateAddress(address.getAddressline(),address.getCity(),address.getState(),address.getPostcode());
            newClient.setAddressid(updatedAddress);
            newClient.setNetworth(c.getNetworth());
            newClient.setLastname(c.getLastname());
            newClient.setFirstname(c.getFirstname());
            newClient.setId(c.getId());
            this.clientService.save(newClient);
        }
    }

    AddressEntity updateAddress(String addressLine,
                                String city,
                                String state,
                                String postCode) {
        List<AddressEntity> addresses = this.addressService.findByParams(
                addressLine,
                city,
                state,
                postCode
        );
        AddressEntity updatedAddress;
        if (addresses.isEmpty()) {
            AddressEntity ae = new AddressEntity();
            ae.setAddressLine(addressLine);
            ae.setCity(city);
            ae.setPostCode(postCode);
            ae.setState(state);
            this.addressService.save(ae);
            updatedAddress = ae;
        } else {
            updatedAddress = addresses.get(0);
        }
        return updatedAddress;
    }

    ClientEntity updateClient(ClientEntity ce,Client c) {
        Address address = c.getAddress();
        AddressEntity updatedAddress = updateAddress(address.getAddressline(),address.getCity(),address.getState(),address.getPostcode());
        ce.setAddressid(updatedAddress);
        ce.setFirstname(c.getFirstname());
        ce.setLastname(c.getLastname());
        ce.setNetworth(c.getNetworth());
        this.clientService.save(ce);
        return ce;
    }
}
