package com.example.demo.adapters.inbound;

import com.example.demo.application.core.locales.Cep;
import com.example.demo.application.core.locales.Municipality;
import com.example.demo.application.core.locales.State;
import com.example.demo.application.ports.in.LocaleServicePort;
import com.example.demo.application.ports.out.SearchLocalesPort;
import com.example.demo.mocks.LocalesMockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LocalesControllerTest {
    @Mock
    private LocaleServicePort localeServicePort;

    @InjectMocks
    private LocalesController localesController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(localesController).build();
    }

    @Test
    void shouldGetLocaleByCep() throws Exception {
        String cep = "04856110";
        Cep mockCep = LocalesMockFactory.createCep();

        when(localeServicePort.getLocaleByCep(cep)).thenReturn(mockCep);

        mockMvc.perform(get("/locale/address/{cep}", cep))
                .andExpect(status().isOk())
                .andReturn();

        verify(localeServicePort, times(1)).getLocaleByCep(cep);
        verifyNoMoreInteractions(localeServicePort);
    }

    @Test
    void shouldGetAllStates() throws Exception {
        State state = LocalesMockFactory.createState();
        List<State> mockStates =List.of(state);

        when(localeServicePort.getStates()).thenReturn(mockStates);

        mockMvc.perform(get("/locale/states"))
                .andExpect(status().isOk())
                .andReturn();

        verify(localeServicePort, times(1)).getStates();
        verifyNoMoreInteractions(localeServicePort);
    }

    @Test
    void shouldGetMunicipalitiesByStateId() throws Exception {
        long stateId = 1L;
        Municipality municipality = LocalesMockFactory.createMunicipality();
        List<Municipality> mockMunicipalities = List.of(municipality);

        when(localeServicePort.getMunicipalitiesByStateID(stateId)).thenReturn(mockMunicipalities);

        mockMvc.perform(get("/locale/municipalities/{stateId}", stateId))
                .andExpect(status().isOk())
                .andReturn();

        verify(localeServicePort, times(1)).getMunicipalitiesByStateID(stateId);
        verifyNoMoreInteractions(localeServicePort);
    }

}