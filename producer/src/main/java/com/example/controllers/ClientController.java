package com.example.controllers;

import com.example.Client;
import com.example.msgfacades.ClientMsg;
import com.example.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @Autowired
    IClientService clientService;

    @GetMapping("/")
    String test() {
        return "AAAAAAAAAAAAAa";
    }

    @PostMapping("/add-new-client")
    String newClient(@RequestBody ClientMsg newClient) {
        return clientService.sndClientMsg(newClient).toString();
    }

    @PutMapping("/edit-else-new-client")
    String editElseNew(@RequestBody ClientMsg client) {
        return clientService.sndClientMsg(client).toString();
    }
}
