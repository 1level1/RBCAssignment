package com.example.msgfacades;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientMsg {
    private String addressline;
    private String city;
    private String state;
    private String postcode;
    private String id;
    private String firstname;
    private Double networth;
    private String lastname;
}
