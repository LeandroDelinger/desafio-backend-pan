package com.example.demo.adapters.inbound;

import com.example.demo.adapters.inbound.request.AddressRequestDTO;
import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.application.core.client.Client;
import com.example.demo.application.ports.in.ClientServicePort;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);
    private final ClientServicePort clientService;

    public ClientController(ClientServicePort clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> save(@Valid @RequestBody ClientRequestDTO client) {
        log.info("Received request to create client: {}", client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Client> getClientByCPF(@PathVariable String cpf) {
        log.info("Received request to get client by CPF: {}", cpf);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClientByCPF(cpf));
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Client> getClientByID(@PathVariable UUID id) {
        log.info("Received request to get client by ID: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClientByID(id));
    }

    @PutMapping("/{clientId}/address")
    public void updateAddress(@PathVariable UUID clientId, @Valid @RequestBody AddressRequestDTO newAddress) {
        log.info("Received request to update client address: {}", newAddress);
        clientService.updateAddress(clientId, newAddress);
    }
}
