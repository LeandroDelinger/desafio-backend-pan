package com.example.demo.adapters.outbound.repository;

import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.adapters.outbound.entities.JpaClientEntity;
import com.example.demo.application.core.client.Client;
import com.example.demo.mocks.ClientMockFactory;
import com.example.demo.utils.mappers.ClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientRepositoryImplTest {
    @Mock
    private JpaClientRepository jpaClientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientRepositoryImpl clientRepositoryImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnClientWhenFindById() {
        UUID clientId = UUID.randomUUID();
        JpaClientEntity jpaClientEntity = ClientMockFactory.createJpaClientEntity();
        Client client = ClientMockFactory.createClient();

        when(jpaClientRepository.findById(clientId)).thenReturn(Optional.of(jpaClientEntity));
        when(clientMapper.toDomain(any(JpaClientEntity.class))).thenReturn(client);

        Optional<Client> result = clientRepositoryImpl.findById(clientId);

        verify(jpaClientRepository, times(1)).findById(clientId);
        verify(clientMapper, times(1)).toDomain(any(JpaClientEntity.class));

        assert(result.isPresent());
        assert(result.get().equals(client));
    }

    @Test
    void shouldReturnClientWhenFindByCPF() {
        String cpf = "12345678900";
        JpaClientEntity jpaClientEntity = ClientMockFactory.createJpaClientEntity();
        Client client = ClientMockFactory.createClient();

        when(jpaClientRepository.findByCPF(cpf)).thenReturn(Optional.of(jpaClientEntity));
        when(clientMapper.toDomain(any(JpaClientEntity.class))).thenReturn(client);

        Optional<Client> result = clientRepositoryImpl.findByCPF(cpf);

        verify(jpaClientRepository, times(1)).findByCPF(cpf);
        verify(clientMapper, times(1)).toDomain(any(JpaClientEntity.class));

        assert(result.isPresent());
        assert(result.get().equals(client));
    }

    @Test
    void shouldSaveClient() {
        ClientRequestDTO clientRequestDTO = ClientMockFactory.createClientRequest();
        JpaClientEntity jpaClientEntity = ClientMockFactory.createJpaClientEntity();
        Client client = ClientMockFactory.createClient();

        when(clientMapper.DTOtoEntity(any(ClientRequestDTO.class))).thenReturn(jpaClientEntity);
        when(jpaClientRepository.save(any(JpaClientEntity.class))).thenReturn(jpaClientEntity);
        when(clientMapper.toDomain(any(JpaClientEntity.class))).thenReturn(client);

        Client result = clientRepositoryImpl.save(clientRequestDTO);

        verify(clientMapper, times(1)).DTOtoEntity(any(ClientRequestDTO.class));
        verify(jpaClientRepository, times(1)).save(any(JpaClientEntity.class));
        verify(clientMapper, times(1)).toDomain(any(JpaClientEntity.class));

        assert(result.equals(client));
    }

    @Test
    void shouldUpdateClientAddress() {
        Client client = ClientMockFactory.createClient();
        JpaClientEntity jpaClientEntity = ClientMockFactory.createJpaClientEntity();

        when(clientMapper.toEntity(any(Client.class))).thenReturn(jpaClientEntity);

        clientRepositoryImpl.updateAddress(client);

        verify(clientMapper, times(1)).toEntity(any(Client.class));
        verify(jpaClientRepository, times(1)).save(any(JpaClientEntity.class));
    }

}