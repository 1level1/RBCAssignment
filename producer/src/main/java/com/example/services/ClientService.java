package com.example.services;

import com.example.Client;
import com.example.conf.AppConfig;
import com.example.kafka.IKafkaProducer;
import com.example.msgfacades.ClientMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements IClientService {
    @Autowired
    IKafkaProducer kafkaProducer;

    public Client sndClientMsg(ClientMsg clntMsg) {
        return kafkaProducer.clientMsg(clntMsg);
    }
}
