package com.example.consumer.doa;

import com.example.consumer.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<AddressEntity, Long> {
    @Query(value = "SELECT a FROM AddressEntity a WHERE a.addressLine = ?1 AND a.city = ?2 AND a.state = ?3 AND a.postCode = ?4")
    List<AddressEntity> findByParams(String addressLine,
                                     String city,
                                     String state,
                                     String postCode);

    Optional<AddressEntity> findById(String id);
    AddressEntity save(AddressEntity ae);
}
