package com.example.demo.application.ports.in;

import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.application.core.client.Client;

import java.util.UUID;

public interface ClientServicePort {
    public Client getClientByID(UUID id);
    public Client getClientByCPF(String cpf);
    public Client save(ClientRequestDTO client);
}
