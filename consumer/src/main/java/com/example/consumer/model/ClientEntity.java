package com.example.consumer.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "clients")
public class ClientEntity {
    @Id
    private String id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="address_id")
    private AddressEntity addressid;
    private String  firstname;
    private Double  networth;
    private String  lastname;
}
