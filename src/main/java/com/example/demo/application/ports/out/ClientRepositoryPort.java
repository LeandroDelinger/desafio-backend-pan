package com.example.demo.application.ports.out;

import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.application.core.client.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepositoryPort {
    Optional<Client> findById(UUID id);
    Optional<Client> findByCPF(String cpf);
    Client save(ClientRequestDTO client);
    void updateAddress(Client client);
}
