package com.example.consumer.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "clients")
public class ClientEntity {
    @Id
    private String id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="client_id")
    private AddressEntity addressid;
    private String  firstname;
    private Double  networth;
    private String  lastname;
}
