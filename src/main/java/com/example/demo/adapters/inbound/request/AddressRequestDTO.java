package com.example.demo.adapters.inbound.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDTO {
    @NotBlank(message = "CEP is required")
    @Size(min = 8, max = 8, message = "CEP must have 8 characters")
    private String cep;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "Number of the residence is required")
    private String number;
    private String complement;
    private String municipality;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "State is required")
    @Size(min = 2, max = 2, message = "State must have 2 letters (ex: SP, RJ)")
    private String state;
}
