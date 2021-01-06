package com.example.consumer.controllers;


import com.example.consumer.model.ClientEntity;
import com.example.consumer.services.AddressService;
import com.example.consumer.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients-api")
public class ClientsController {
    ClientService clientService;
    AddressService addressService;

    @Autowired
    public ClientsController(ClientService clientService,AddressService addressService) {
        this.clientService = clientService;
        this.addressService = addressService;
    }

    @GetMapping(value="/clients")
    public List<ClientEntity> getAllClients(){
        return clientService.getAllClients();
    }
}
