package com.example.demo.adapters.outbound.repository;

import com.example.demo.adapters.inbound.ClientController;
import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.adapters.outbound.entities.JpaClientEntity;
import com.example.demo.application.core.Address.Address;
import com.example.demo.application.core.client.Client;
import com.example.demo.application.ports.out.ClientRepositoryPort;
import com.example.demo.utils.mappers.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepositoryPort {
    private static final Logger log = LoggerFactory.getLogger(ClientRepositoryImpl.class);
    private final JpaClientRepository jpaClientRepository;
    private final ClientMapper clientMapper;

    @Override
    public Optional<Client> findById(UUID id) {
        log.info("Fetching client by ID: {}", id);
        Optional<JpaClientEntity> jpaClientEntity = jpaClientRepository.findById(id);
        return jpaClientEntity.map(clientMapper::toDomain);
    }

    @Override
    public Optional<Client> findByCPF(String cpf) {
        log.info("Fetching client by CPF: {}", cpf);
        Optional<JpaClientEntity> jpaClientEntity = jpaClientRepository.findByCPF(cpf);
        return jpaClientEntity.map(clientMapper::toDomain);
    }

    @Override
    public Client save(ClientRequestDTO client) {
        log.info("Saving new client: {}", client);
        JpaClientEntity jpaClientEntity = clientMapper.DTOtoEntity(client);
        return clientMapper.toDomain(jpaClientRepository.save(jpaClientEntity));
    }

    @Override
    public void updateAddress(Client client) {
        log.info("Updating address for client ID: {}", client.getId());
        JpaClientEntity jpaClientEntity = clientMapper.toEntity(client);
        jpaClientRepository.save(jpaClientEntity);
    }
}
