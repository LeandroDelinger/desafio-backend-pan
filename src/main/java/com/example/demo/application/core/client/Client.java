package com.example.demo.application.core.client;

import com.example.demo.application.core.adress.Adress;

import java.time.LocalDate;
import java.util.UUID;

public class Client {
    private UUID id;
    private String cpf;
    private String name;
    private LocalDate dateOfBirth;
    private Adress adress;

    public Client() {}

    public Client(UUID id, String cpf, String name, LocalDate dateOfBirth, Adress adress) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.adress = adress;
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

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
