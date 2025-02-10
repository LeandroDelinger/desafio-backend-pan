package com.example.demo.adapters.outbound.rest;

import com.example.demo.adapters.outbound.dto.CepResponseDTO;
import com.example.demo.adapters.outbound.dto.MunicipalityFullDTO;
import com.example.demo.adapters.outbound.dto.StateResponseDTO;
import com.example.demo.mocks.LocalesMockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalesApiClientTest {
    @Mock
    private RestTemplate restTemplate;

    private LocalesApiClient localesApiClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        localesApiClient = new LocalesApiClient(restTemplate);
    }

    @Test
    void shouldReturnCepResponseWhenValidCep() {
        CepResponseDTO expectedResponse = LocalesMockFactory.cepResponseDTO();

        when(restTemplate.getForObject(anyString(), eq(CepResponseDTO.class)))
                .thenReturn(expectedResponse);

        Optional<CepResponseDTO> actualResponse = localesApiClient.getAddress("04856110");

        assertTrue(actualResponse.isPresent());
        assertEquals(expectedResponse.getUf(), actualResponse.get().getUf());

        verify(restTemplate, times(1)).getForObject(anyString(), any());
    }

    @Test
    void shouldReturnEmptyOptionalWhenCepNotFound() {
        when(restTemplate.getForObject(anyString(), eq(CepResponseDTO.class)))
                .thenReturn(null);

        Optional<CepResponseDTO> actualResponse = localesApiClient.getAddress("00000000");

        assertFalse(actualResponse.isPresent());

        verify(restTemplate, times(1)).getForObject(anyString(), eq(CepResponseDTO.class));
    }

    @Test
    void shouldReturnStatesWhenValidResponse() {
        StateResponseDTO expectedState = LocalesMockFactory.stateResponseDTO();

        when(restTemplate.getForObject(anyString(), eq(StateResponseDTO[].class)))
                .thenReturn(new StateResponseDTO[]{expectedState});

        List<StateResponseDTO> actualStates = localesApiClient.getStates();

        assertNotNull(actualStates);
        assertEquals(1, actualStates.size());
        assertEquals(expectedState.getNome(), actualStates.get(0).getNome());

        verify(restTemplate, times(1)).getForObject(anyString(), eq(StateResponseDTO[].class));
    }

    @Test
    void shouldReturnEmptyListWhenStatesFetchFails() {
        when(restTemplate.getForObject(anyString(), eq(StateResponseDTO[].class)))
                .thenThrow(new RuntimeException("Error fetching states"));

        List<StateResponseDTO> actualStates = localesApiClient.getStates();

        assertTrue(actualStates.isEmpty());

        verify(restTemplate, times(1)).getForObject(anyString(), eq(StateResponseDTO[].class));
    }

    @Test
    void shouldReturnMunicipalitiesWhenValidStateId() {
        MunicipalityFullDTO expectedMunicipality = LocalesMockFactory.municipalityFullDTO();

        when(restTemplate.getForObject(anyString(), eq(MunicipalityFullDTO[].class)))
                .thenReturn(new MunicipalityFullDTO[]{expectedMunicipality});

        List<MunicipalityFullDTO> actualMunicipalities = localesApiClient.getMunicipalitiesByStateID(35L);

        assertNotNull(actualMunicipalities);
        assertEquals(1, actualMunicipalities.size());
        assertEquals(expectedMunicipality.getNome(), actualMunicipalities.get(0).getNome());

        verify(restTemplate, times(1)).getForObject(anyString(), eq(MunicipalityFullDTO[].class));
    }

    @Test
    void shouldReturnEmptyListWhenMunicipalitiesFetchFails() {
        when(restTemplate.getForObject(anyString(), eq(MunicipalityFullDTO[].class)))
                .thenThrow(new RuntimeException("Error fetching municipalities"));

        List<MunicipalityFullDTO> actualMunicipalities = localesApiClient.getMunicipalitiesByStateID(35L);

        assertTrue(actualMunicipalities.isEmpty());

        verify(restTemplate, times(1)).getForObject(anyString(), eq(MunicipalityFullDTO[].class));
    }
}
