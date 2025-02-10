package com.example.demo.adapters.inbound.response;

import com.example.demo.application.core.address.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClientResponseDTO {
    private UUID id;
    private String cpf;
    private String name;
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    private Address address;
}
