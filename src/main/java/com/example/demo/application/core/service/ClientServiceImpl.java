package com.example.demo.application.core.service;

import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.application.core.client.Client;
import com.example.demo.application.ports.in.ClientServicePort;
import com.example.demo.application.ports.out.ClientRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientServicePort {
    private final ClientRepositoryPort clientRepository;

    public ClientServiceImpl(ClientRepositoryPort clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClientByID(UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client getClientByCPF(String cpf) {
        return clientRepository.findByCPF(cpf).orElse(null);
    }

    @Override
    public Client save(ClientRequestDTO client) {
        return clientRepository.save(client);
    }
}
