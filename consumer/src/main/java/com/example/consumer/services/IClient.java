package com.example.consumer.services;

import com.example.consumer.model.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface IClient {
    ClientEntity save(ClientEntity ce);
    Optional<ClientEntity> findById(String id);
    List<ClientEntity> getAllClients();
}
