package com.example.kafka;

import com.example.Client;
import com.example.msgfacades.ClientMsg;

public interface IKafkaProducer {
    public Client clientMsg(ClientMsg client);
}
