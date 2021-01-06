package com.example.consumer.services;

import com.example.consumer.doa.ClientsRepo;
import com.example.consumer.model.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClient{
    ClientsRepo clientsRepo;
    @Autowired
    public ClientService(ClientsRepo clientsRepo) {
        this.clientsRepo=clientsRepo;
    }

    @Override
    public ClientEntity save(ClientEntity ce) {
        return clientsRepo.save(ce);
    }

    @Override
    public Optional<ClientEntity> findById(String id) {
        return clientsRepo.findById(id);
    }

    @Override
    public List<ClientEntity> getAllClients() {
        return clientsRepo.findAll();
    }
}
