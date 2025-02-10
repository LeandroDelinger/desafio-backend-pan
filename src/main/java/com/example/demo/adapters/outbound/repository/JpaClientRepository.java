package com.example.demo.adapters.outbound.repository;

import com.example.demo.adapters.outbound.entities.JpaClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface JpaClientRepository extends JpaRepository<JpaClientEntity, UUID> {
    @Query("SELECT c FROM JpaClientEntity c LEFT JOIN FETCH c.address WHERE c.cpf = :cpf")
    Optional<JpaClientEntity> findByCPF(String cpf);
}
