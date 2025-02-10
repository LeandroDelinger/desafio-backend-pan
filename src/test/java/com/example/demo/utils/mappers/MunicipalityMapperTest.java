package com.example.demo.utils.mappers;

import com.example.demo.adapters.outbound.dto.MunicipalityFullDTO;
import com.example.demo.application.core.locales.Municipality;
import com.example.demo.mocks.LocalesMockFactory;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class MunicipalityMapperTest {
    private final MunicipalityMapper municipalityMapper = Mappers.getMapper(MunicipalityMapper.class);

    @Test
    void shouldMapMunicipalityFullDTOToMunicipality() {
        MunicipalityFullDTO dto = LocalesMockFactory.municipalityFullDTO();

        Municipality municipality = municipalityMapper.DTOtoDomain(dto);
        assertNotNull(municipality);
        assertEquals(dto.getNome(), municipality.getName());
        assertEquals(municipality.getState(), municipality.getState());
        assertEquals(municipality.getRegion(), municipality.getRegion());
    }

}