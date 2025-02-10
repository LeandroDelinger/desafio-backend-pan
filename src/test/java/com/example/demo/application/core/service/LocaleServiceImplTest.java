package com.example.demo.application.core.service;

import com.example.demo.application.core.locales.Cep;
import com.example.demo.application.core.locales.Municipality;
import com.example.demo.application.core.locales.State;
import com.example.demo.application.exceptions.CepNotFoundException;
import com.example.demo.application.ports.out.SearchLocalesPort;
import com.example.demo.mocks.LocalesMockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocaleServiceImplTest {
    @Mock
    private SearchLocalesPort searchLocalesPort;

    @InjectMocks
    private LocaleServiceImpl localeServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCepWhenFound() {
        String cep = "04856110";
        Cep expectedCep = LocalesMockFactory.createCep();

        when(searchLocalesPort.searchAddressWithCEP(cep)).thenReturn(Optional.of(expectedCep));

        Cep actualCep = localeServiceImpl.getLocaleByCep(cep);

        assertNotNull(actualCep);
        assertEquals(expectedCep, actualCep);

        verify(searchLocalesPort, times(1)).searchAddressWithCEP(cep);
    }

    @Test
    void shouldThrowCepNotFoundExceptionWhenNotFound() {
        String cep = "00000000";

        when(searchLocalesPort.searchAddressWithCEP(cep)).thenReturn(Optional.empty());

        CepNotFoundException exception = assertThrows(CepNotFoundException.class, () -> localeServiceImpl.getLocaleByCep(cep));
        assertEquals("No locale found with cep " + cep, exception.getMessage());

        verify(searchLocalesPort, times(1)).searchAddressWithCEP(cep);
    }

    @Test
    void shouldReturnStates() {
        State state = LocalesMockFactory.createState();
        List<State> expectedStates = List.of(state);

        when(searchLocalesPort.getStates()).thenReturn(expectedStates);

        List<State> actualStates = localeServiceImpl.getStates();

        assertNotNull(actualStates);
        assertEquals(expectedStates, actualStates);

        verify(searchLocalesPort, times(1)).getStates();
    }

    @Test
    void shouldReturnMunicipalitiesByStateId() {
        long stateId = 1L;
        Municipality municipality = LocalesMockFactory.createMunicipality();
        List<Municipality> expectedMunicipalities = List.of(municipality);

        when(searchLocalesPort.getMunicipalitiesByStateID(stateId)).thenReturn(expectedMunicipalities);

        List<Municipality> actualMunicipalities = localeServiceImpl.getMunicipalitiesByStateID(stateId);

        assertNotNull(actualMunicipalities);
        assertEquals(expectedMunicipalities, actualMunicipalities);

        verify(searchLocalesPort, times(1)).getMunicipalitiesByStateID(stateId);
    }
}