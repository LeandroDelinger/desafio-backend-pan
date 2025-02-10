package com.example.demo.adapters.inbound;

import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.application.core.client.Client;
import com.example.demo.application.ports.in.ClientServicePort;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientServicePort clientService;

    public ClientController(ClientServicePort clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client save(@RequestBody ClientRequestDTO client) {
        return clientService.save(client);
    }

    @GetMapping("/{cpf}")
    public Client getClientByCPF(@PathVariable String cpf) {
        return clientService.getClientByCPF(cpf);
    }


    @GetMapping("/id/{id}")
    public Client getClientByID(@PathVariable UUID id) {
        return clientService.getClientByID(id);
    }
}
