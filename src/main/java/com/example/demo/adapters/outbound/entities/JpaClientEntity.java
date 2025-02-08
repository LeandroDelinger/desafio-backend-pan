package com.example.demo.adapters.outbound.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "client")
@Entity
public class JpaClientEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    private String name;
    private LocalDate dateOfBirth;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adress_id")
    private JpaAdressEntity adress;

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

    public JpaAdressEntity getAdress() {
        return adress;
    }

    public void setAdress(JpaAdressEntity adress) {
        this.adress = adress;
    }
}
