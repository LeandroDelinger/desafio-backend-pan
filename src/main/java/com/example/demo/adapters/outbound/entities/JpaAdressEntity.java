package com.example.demo.adapters.outbound.entities;

import com.example.demo.application.core.adress.Adress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name = "adress")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class JpaAdressEntity {
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
