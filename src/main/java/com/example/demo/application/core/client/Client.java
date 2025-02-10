package com.example.demo.application.core.client;

import com.example.demo.application.core.Address.Address;

import java.time.LocalDate;
import java.util.UUID;

public class Client {
    private UUID id;
    private String cpf;
    private String name;
    private LocalDate dateOfBirth;
    private Address address;

    public Client() {}

    public Client(UUID id, String cpf, String name, LocalDate dateOfBirth, Address address) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
