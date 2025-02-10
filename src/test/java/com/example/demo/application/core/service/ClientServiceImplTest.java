package com.example.demo.application.core.service;

import com.example.demo.adapters.inbound.request.AddressRequestDTO;
import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.application.core.client.Client;
import com.example.demo.application.exceptions.ClientAlreadyExistsException;
import com.example.demo.application.exceptions.ClientNotFoundException;
import com.example.demo.application.ports.out.ClientRepositoryPort;
import com.example.demo.mocks.ClientMockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {
    @Mock
    private ClientRepositoryPort clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;
    private UUID clientId;
    private ClientRequestDTO clientRequestDTO;
    private AddressRequestDTO addressRequestDTO;
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientId = UUID.randomUUID();
        clientRequestDTO = ClientMockFactory.createClientRequest();
        addressRequestDTO = ClientMockFactory.createAddressRequest();
        client = ClientMockFactory.createClient();
    }


    @Test
    void shouldGetClientByID() {
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        Client foundClient = clientService.getClientByID(client.getId());

        assertNotNull(foundClient);
        assertEquals(client.getId(), foundClient.getId());
    }

    @Test
    void shouldThrowClientNotFoundExceptionWhenClientNotFoundByID() {
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientService.getClientByID(clientId));
    }

    @Test
    void shouldGetClientByCPF() {
        when(clientRepository.findByCPF("12345678900")).thenReturn(Optional.of(client));

        Client foundClient = clientService.getClientByCPF("12345678900");

        assertNotNull(foundClient);
        assertEquals("12345678900", foundClient.getCpf());
        verify(clientRepository, times(1)).findByCPF("12345678900");
    }

    @Test
    void shouldThrowClientNotFoundExceptionWhenClientNotFoundByCPF() {
        when(clientRepository.findByCPF("12345678900")).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientService.getClientByCPF("12345678900"));
    }

    @Test
    void shouldSaveClient() {
        when(clientRepository.save(any(ClientRequestDTO.class))).thenReturn(client);

        Client savedClient = clientService.save(clientRequestDTO);

        assertNotNull(savedClient);
        assertEquals("John Doe", savedClient.getName());
        verify(clientRepository, times(1)).save(any(ClientRequestDTO.class));
    }

    @Test
    void shouldThrowClientAlreadyExistsExceptionWhenSavingClientWithExistingCPF() {
        when(clientRepository.findByCPF("12345678900")).thenReturn(Optional.of(client));

        assertThrows(ClientAlreadyExistsException.class, () -> clientService.save(clientRequestDTO));
    }

    @Test
    void shouldUpdateClientAddress() {
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        clientService.updateAddress(clientId, addressRequestDTO);

        verify(clientRepository, times(1)).updateAddress(any(Client.class));
    }
}