package com.example.demo.adapters.outbound.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name = "address")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class JpaAddressEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;

    private String cep;
    private String street;
    private String number;
    private String complement;
    private String municipality;
    private String city;
    private String state;

}
