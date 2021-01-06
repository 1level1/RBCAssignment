package com.example.consumer.services;

import com.example.consumer.model.AddressEntity;
import com.example.consumer.model.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface IAddress {
    AddressEntity save(AddressEntity ae);
    Optional<AddressEntity> findById(String id);
//    List<AddressEntity> getAllClients();
    List<AddressEntity> findByParams(String addressLine,
                                         String city,
                                         String state,
                                         String postCode
    );
}
