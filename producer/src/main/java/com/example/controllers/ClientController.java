package com.example.controllers;

import com.example.Client;
import com.example.msgfacades.ClientMsg;
import com.example.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import javax.validation.Valid;

@RestController
public class ClientController {
    @Autowired
    IClientService clientService;

    boolean checkClient(ClientMsg cm) {
        if (cm==null)
            return false;
        if (cm.getAddressline()==null || cm.getCity()==null || cm.getState()==null ||
            cm.getFirstname()==null || cm.getId()==null || cm.getLastname() == null ||
            cm.getNetworth()==null || cm.getPostcode() == null)
            return false;
        return true;
    }

    ResponseEntity<String> sndMsg(ClientMsg newClient) {
        if (!checkClient(newClient)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        try {
            clientService.sndClientMsg(newClient);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/add-new-client")
    ResponseEntity<String> newClient(@Valid @RequestBody ClientMsg newClient) {
        return sndMsg(newClient);
    }

    @PutMapping("/edit-else-new-client")
    ResponseEntity<String> editElseNew(@Valid @RequestBody ClientMsg newClient) {
        return sndMsg(newClient);
    }
}
