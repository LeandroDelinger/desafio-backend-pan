package com.example.demo.utils.mappers;

import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.adapters.outbound.entities.JpaClientEntity;
import com.example.demo.application.core.client.Client;
import com.example.demo.mocks.ClientMockFactory;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapperTest {
    private final ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

    @Test
    void shouldMapJpaClientEntityToClient() {
        JpaClientEntity entity = ClientMockFactory.createJpaClientEntity();

        Client client = clientMapper.toDomain(entity);

        assertNotNull(client);
        assertEquals(entity.getName(), client.getName());
        assertEquals(entity.getAddress().getCep(), client.getAddress().getCep());
    }

    @Test
    void shouldMapClientToJpaClientEntity() {
        Client client = ClientMockFactory.createClient();

        JpaClientEntity entity = clientMapper.toEntity(client);

        assertNotNull(entity);
        assertEquals(client.getName(), entity.getName());
        assertEquals(client.getAddress().getStreet(), entity.getAddress().getStreet());
    }

    @Test
    void shouldMapClientRequestDTOToClient() {
        ClientRequestDTO dto = ClientMockFactory.createClientRequest();

        Client client = clientMapper.DTOtoDomain(dto);

        assertNotNull(client);
        assertEquals(dto.getName(), client.getName());
        assertEquals(dto.getAddress().getCep(), client.getAddress().getCep());
    }

    @Test
    void shouldMapClientRequestDTOToJpaClientEntity() {
        ClientRequestDTO dto = ClientMockFactory.createClientRequest();

        JpaClientEntity entity = clientMapper.DTOtoEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getAddress().getCep(), entity.getAddress().getCep());
    }

}