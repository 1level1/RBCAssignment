package com.example.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "addresses")
public class AddressEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String addressLine;
    private String city;
    private String state;
    private String postCode;
}
