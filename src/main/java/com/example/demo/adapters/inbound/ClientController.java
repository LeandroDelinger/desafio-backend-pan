package com.example.demo.adapters.inbound;

import com.example.demo.adapters.inbound.request.AddressRequestDTO;
import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.adapters.inbound.response.ClientResponseDTO;
import com.example.demo.application.core.client.Client;
import com.example.demo.application.ports.in.ClientServicePort;
import com.example.demo.utils.mappers.ClientMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);
    private final ClientServicePort clientService;
    private final ClientMapper clientMapper;

    public ClientController(ClientServicePort clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> save(@Valid @RequestBody ClientRequestDTO client) {
        log.info("Received request to create client: {}", client);
        Client newClient = clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientMapper.toResponseDTO(newClient));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClientResponseDTO> getClientByCPF(@PathVariable String cpf) {
        log.info("Received request to get client by CPF: {}", cpf);
        Client client = clientService.getClientByCPF(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(clientMapper.toResponseDTO(client));
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<ClientResponseDTO> getClientByID(@PathVariable UUID id) {
        log.info("Received request to get client by ID: {}", id);
        Client client = clientService.getClientByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientMapper.toResponseDTO(client));
    }

    @PutMapping("/{clientId}/address")
    public void updateAddress(@PathVariable UUID clientId, @Valid @RequestBody AddressRequestDTO newAddress) {
        log.info("Received request to update client address: {}", newAddress);
        clientService.updateAddress(clientId, newAddress);
    }
}
