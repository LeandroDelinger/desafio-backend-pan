package com.example.demo.adapters.inbound;

import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.application.core.client.Client;
import com.example.demo.application.ports.in.ClientServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/client")
@Tag(name = "Cliente", description = "API para gerenciamento de clientes")
public class ClientController {
    private final ClientServicePort clientService;

    public ClientController(ClientServicePort clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Buscar cliente por ID", description = "Retorna os dados do cliente com base no ID informado")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)))
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    @GetMapping("/id/{id}")
    public Client getClientByCPF(@PathVariable UUID id) {
        return clientService.getClientByID(id);
    }

    @Operation(summary = "Buscar cliente por CPF", description = "Retorna os dados do cliente com base no CPF informado")
    @ApiResponse(
            responseCode = "200",
            description = "Cliente encontrado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Cliente não encontrado"
    )
    @GetMapping("/{cpf}")
    public Client getClientByCPF(@PathVariable String cpf) {
        return clientService.getClientByCPF(cpf);
    }

    @Operation(summary = "Cadastrar um novo cliente", description = "Salva um novo cliente na base de dados")
    @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @PostMapping
    public Client save(@RequestBody ClientRequestDTO client) {
        return clientService.save(client);
    }


}
