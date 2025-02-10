package com.example.demo.application.core.service;

import com.example.demo.adapters.inbound.request.AddressRequestDTO;
import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.application.core.address.Address;
import com.example.demo.application.core.client.Client;
import com.example.demo.application.exceptions.ClientAlreadyExistsException;
import com.example.demo.application.exceptions.ClientNotFoundException;
import com.example.demo.application.ports.in.ClientServicePort;
import com.example.demo.application.ports.out.ClientRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientServicePort {
    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
    private final ClientRepositoryPort clientRepository;

    public ClientServiceImpl(ClientRepositoryPort clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClientByID(UUID id) {
        log.info("executing get client by ID: {}", id);
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("No client found with id " + id));
        log.info("client found: {}", client.toString());
        return client;
    }

    @Override
    public Client getClientByCPF(String cpf) {
        log.info("executing get client by CPF: {}", cpf);
        Client client = clientRepository.findByCPF(cpf).orElseThrow(() -> new ClientNotFoundException("No client found with cpf " + cpf));
        log.info("client found: {}", client.toString());
        return client;
    }

    @Override
    public Client save(ClientRequestDTO client) {
        log.info("executing save client: {}", client);
        clientRepository.findByCPF(client.getCpf()).ifPresent(c -> {
            throw new ClientAlreadyExistsException("Client with cpf " + client.getCpf() + " already exists");
        });
        Client newClient = clientRepository.save(client);
        log.info("client saved: {}", newClient.toString());
        return newClient;
    }

    @Override
    public void updateAddress(UUID clientId, AddressRequestDTO newAddress) {
        log.info("executing update client address: {}", newAddress);
        Client client = this.getClientByID(clientId);

        Address address = new Address();
        address.setCep(newAddress.getCep());
        address.setStreet(newAddress.getStreet());
        address.setNumber(newAddress.getNumber());
        address.setComplement(newAddress.getComplement());
        address.setMunicipality(newAddress.getMunicipality());
        address.setCity(newAddress.getCity());
        address.setState(newAddress.getState());

        client.setAddress(address);
        log.info("updating client address: {}", client.toString());
        clientRepository.updateAddress(client);
        log.info("client address updated: {}", client.toString());
    }
}
