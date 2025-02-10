package com.example.demo.utils.mappers;

import com.example.demo.adapters.outbound.dto.StateResponseDTO;
import com.example.demo.application.core.locales.State;
import com.example.demo.mocks.LocalesMockFactory;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class StateMapperTest {
    private final StateMapper stateMapper = Mappers.getMapper(StateMapper.class);

    @Test
    void shouldMapStateResponseDTOToState() {
        StateResponseDTO dto = LocalesMockFactory.stateResponseDTO();

        State state = stateMapper.DTOtoDomain(dto);

        assertNotNull(state);
        assertEquals(dto.getId(), state.getId());
        assertEquals(dto.getSigla(), state.getAcronym());
        assertEquals(dto.getNome(), state.getName());
    }

}