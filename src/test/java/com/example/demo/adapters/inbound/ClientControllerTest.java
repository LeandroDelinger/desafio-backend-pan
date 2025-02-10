package com.example.demo.adapters.inbound;

import com.example.demo.adapters.inbound.request.AddressRequestDTO;
import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.application.core.client.Client;
import com.example.demo.application.ports.in.ClientServicePort;
import com.example.demo.mocks.ClientMockFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
    @Mock
    private ClientServicePort clientService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientController;  // Controller que você está testando

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }


    @Test
    public void shouldReceiveRequestAndCreateNewClient() throws Exception {
        when(clientService.save(any(ClientRequestDTO.class))).thenReturn(ClientMockFactory.createClient());
        ClientRequestDTO request = ClientMockFactory.createClientRequest();

        mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        verify(clientService, times(1)).save(any());
    }

    @Test
    public void shouldGetClientByCPF() throws Exception {
        when(clientService.getClientByCPF("12345678900")).thenReturn(ClientMockFactory.createClient());
        String cpf = "12345678900";

        mockMvc.perform(get("/client/" + cpf))
                .andExpect(status().isOk())
                .andReturn();

        verify(clientService, times(1)).getClientByCPF(cpf);
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void shouldGetClientByID() throws Exception {
        when(clientService.getClientByID(any())).thenReturn(ClientMockFactory.createClient());
        String id = "123e4567-e89b-12d3-a456-426614174000";

        mockMvc.perform(get("/client/id/" + id))
                .andExpect(status().isOk())
                .andReturn();

        verify(clientService, times(1)).getClientByID(UUID.fromString(id));
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void shouldUpdateClientAddress() throws Exception {
        UUID clientId = UUID.randomUUID();
        AddressRequestDTO newAddress = ClientMockFactory.createAddressRequest();
        doNothing().when(clientService).updateAddress(clientId, newAddress);

        mockMvc.perform(put("/client/" + clientId + "/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newAddress)))
                .andExpect(status().isOk())
                .andReturn();

        verify(clientService, times(1)).updateAddress(any(), any());
        verifyNoMoreInteractions(clientService);
    }
}