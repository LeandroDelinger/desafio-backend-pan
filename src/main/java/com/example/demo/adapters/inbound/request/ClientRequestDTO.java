package com.example.demo.adapters.inbound.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "CPF is required")
    @Size(min = 11, max = 11, message = "CPF must have 11 characters")
    private String cpf;

    @NotNull(message = "Date of birth is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @NotNull(message = "Address is required")
    @Valid
    private AddressRequestDTO address;
}
