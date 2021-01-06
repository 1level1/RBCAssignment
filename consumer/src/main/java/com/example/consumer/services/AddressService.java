package com.example.consumer.services;

import com.example.consumer.doa.AddressRepo;
import com.example.consumer.doa.ClientsRepo;
import com.example.consumer.model.AddressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements IAddress {
    AddressRepo addressRepo;

    @Autowired
    public AddressService(AddressRepo addressRepo) {
        this.addressRepo=addressRepo;
    }

    @Override
    public AddressEntity save(AddressEntity ae) {
        return addressRepo.save(ae);
    }

    @Override
    public Optional<AddressEntity> findById(String id) {
        return addressRepo.findById(id);
    }

    @Override
    public List<AddressEntity> findByParams(String addressLine,
                                            String city,
                                            String state,
                                            String postCode) {
        return addressRepo.findByParams(addressLine,city,state,postCode);
    }
}
