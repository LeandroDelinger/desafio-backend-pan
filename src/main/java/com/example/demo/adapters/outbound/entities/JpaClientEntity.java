package com.example.demo.adapters.outbound.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private JpaAddressEntity address;

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

    public JpaAddressEntity getAddress() {
        return address;
    }

    public void setAddress(JpaAddressEntity address) {
        this.address = address;
    }
}
