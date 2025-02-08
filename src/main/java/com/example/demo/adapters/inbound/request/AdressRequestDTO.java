package com.example.demo.adapters.inbound.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdressRequestDTO {
    private String cep;
    private String street;
    private String number;
    private String complement;
    private String municipality;
    private String city;
    private String state;
}
