package com.example.demo.adapters.outbound.repository;

import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.adapters.outbound.entities.JpaClientEntity;
import com.example.demo.application.core.client.Client;
import com.example.demo.application.ports.out.ClientRepositoryPort;
import com.example.demo.utils.mappers.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepositoryPort {
    private final JpaClientRepository jpaClientRepository;
    private final ClientMapper clientMapper;

    @Override
    public Optional<Client> findById(UUID id) {
        Optional<JpaClientEntity> jpaClientEntity = jpaClientRepository.findById(id);
        return jpaClientEntity.map(clientMapper::toDomain);
    }

    @Override
    public Optional<Client> findByCPF(String cpf) {
        Optional<JpaClientEntity> jpaClientEntity = jpaClientRepository.findByCPF(cpf);
        return jpaClientEntity.map(clientMapper::toDomain);
    }

    @Override
    public Client save(ClientRequestDTO client) {
        JpaClientEntity jpaClientEntity = clientMapper.DTOtoEntity(client);
        return clientMapper.toDomain(jpaClientRepository.save(jpaClientEntity));
    }
}
