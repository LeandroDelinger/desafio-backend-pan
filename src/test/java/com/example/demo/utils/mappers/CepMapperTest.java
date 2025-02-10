package com.example.demo.utils.mappers;

import com.example.demo.adapters.outbound.dto.CepResponseDTO;
import com.example.demo.application.core.locales.Cep;
import com.example.demo.mocks.LocalesMockFactory;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CepMapperTest {


    private final CepMapper cepMapper = Mappers.getMapper(CepMapper.class);

    @Test
    void shouldMapCepResponseDTOtoCep() {
        CepResponseDTO dto = LocalesMockFactory.cepResponseDTO();

        Cep cep = cepMapper.DTOtoDomain(dto);

        assertNotNull(cep);
        assertEquals(dto.getCep(), cep.getCep());
        assertEquals(dto.getBairro(), cep.getNeighborhood());
        assertEquals(dto.getLogradouro(), cep.getPatio());
    }
}