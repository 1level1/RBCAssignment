package com.example.consumer.doa;

import com.example.consumer.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientsRepo extends JpaRepository<ClientEntity, String> {
    Optional<ClientEntity> findById(String id);

}
