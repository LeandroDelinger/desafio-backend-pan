package com.example.demo.adapters.outbound.rest;

import com.example.demo.adapters.outbound.dto.CepResponseDTO;
import com.example.demo.adapters.outbound.dto.MunicipalityFullDTO;
import com.example.demo.adapters.outbound.dto.StateResponseDTO;
import com.example.demo.application.core.locales.Cep;
import com.example.demo.application.core.locales.Municipality;
import com.example.demo.application.core.locales.State;
import com.example.demo.mocks.LocalesMockFactory;
import com.example.demo.utils.mappers.CepMapper;
import com.example.demo.utils.mappers.MunicipalityMapper;
import com.example.demo.utils.mappers.StateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchLocalesAdapterTest {

    @Mock
    private LocalesApiClient localesApiClient;

    @Mock
    private CepMapper cepMapper;

    @Mock
    private StateMapper stateMapper;

    @Mock
    private MunicipalityMapper municipalityMapper;

    @InjectMocks
    private SearchLocalesAdapter searchLocalesAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCepWhenFound() {
        String cep = "04856110";
        Cep expectedCep = LocalesMockFactory.createCep();

        when(localesApiClient.getAddress(cep)).thenReturn(Optional.of(LocalesMockFactory.cepResponseDTO()));
        when(cepMapper.DTOtoDomain(any(CepResponseDTO.class))).thenReturn(expectedCep);

        Optional<Cep> actualCep = searchLocalesAdapter.searchAddressWithCEP(cep);

        assertTrue(actualCep.isPresent());
        assertEquals(expectedCep, actualCep.get());

        verify(localesApiClient, times(1)).getAddress(cep);
        verify(cepMapper, times(1)).DTOtoDomain(any(CepResponseDTO.class));
    }

    @Test
    void shouldReturnEmptyOptionalWhenCepNotFound() {
        String cep = "00000000";
        when(localesApiClient.getAddress(cep)).thenReturn(Optional.empty());

        Optional<Cep> actualCep = searchLocalesAdapter.searchAddressWithCEP(cep);

        assertFalse(actualCep.isPresent());
        verify(localesApiClient, times(1)).getAddress(cep);
    }

    @Test
    void shouldReturnStates() {
        State state = LocalesMockFactory.createState();
        List<State> expectedStates = List.of(state);

        when(localesApiClient.getStates()).thenReturn(Arrays.asList(new StateResponseDTO()));
        when(stateMapper.DTOtoDomain(any(StateResponseDTO.class))).thenReturn(state);

        List<State> actualStates = searchLocalesAdapter.getStates();

        assertNotNull(actualStates);
        assertEquals(expectedStates, actualStates);

        verify(localesApiClient, times(1)).getStates();
        verify(stateMapper, times(1)).DTOtoDomain(any(StateResponseDTO.class));
    }

    @Test
    void shouldReturnMunicipalitiesByStateId() {
        long stateId = 1L;
        Municipality municipality = LocalesMockFactory.createMunicipality();
        List<Municipality> expectedMunicipalities = List.of(municipality);

        when(localesApiClient.getMunicipalitiesByStateID(stateId)).thenReturn(Arrays.asList(new MunicipalityFullDTO()));
        when(municipalityMapper.DTOtoDomain(any(MunicipalityFullDTO.class))).thenReturn(municipality);

        List<Municipality> actualMunicipalities = searchLocalesAdapter.getMunicipalitiesByStateID(stateId);

        assertNotNull(actualMunicipalities);
        assertEquals(expectedMunicipalities, actualMunicipalities);

        verify(localesApiClient, times(1)).getMunicipalitiesByStateID(stateId);
        verify(municipalityMapper, times(1)).DTOtoDomain(any(MunicipalityFullDTO.class));
    }
}
