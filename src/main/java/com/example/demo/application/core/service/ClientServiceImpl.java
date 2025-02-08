package com.example.demo.application.core.service;

import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.application.core.client.Client;
import com.example.demo.application.exceptions.ClientAlreadyExistsException;
import com.example.demo.application.exceptions.ClientNotFoundException;
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
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("No client found with id " + id));
    }

    @Override
    public Client getClientByCPF(String cpf) {
        return clientRepository.findByCPF(cpf).orElseThrow(() -> new ClientNotFoundException("No client found with cpf " + cpf));
    }

    @Override
    public Client save(ClientRequestDTO client) {
        clientRepository.findByCPF(client.getCpf()).ifPresent(c -> {
            throw new ClientAlreadyExistsException("Client with cpf " + client.getCpf() + " already exists");
        });
        return clientRepository.save(client);
    }
}
