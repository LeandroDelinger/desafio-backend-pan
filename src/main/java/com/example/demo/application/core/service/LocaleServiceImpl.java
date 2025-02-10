package com.example.demo.application.core.service;

import com.example.demo.application.core.locales.Cep;
import com.example.demo.application.core.locales.Municipality;
import com.example.demo.application.core.locales.State;
import com.example.demo.application.exceptions.CepNotFoundException;
import com.example.demo.application.ports.in.LocaleServicePort;
import com.example.demo.application.ports.out.SearchLocalesPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocaleServiceImpl implements LocaleServicePort {
    private final SearchLocalesPort searchLocalesPort;

    public LocaleServiceImpl(SearchLocalesPort searchLocalesPort) {
        this.searchLocalesPort = searchLocalesPort;
    }

    @Override
    public Cep getLocaleByCep(String cep) {
        return searchLocalesPort.searchAddressWithCEP(cep)
                .filter(this::isValidCep)
                .orElseThrow(() -> new CepNotFoundException("No locale found with cep " + cep));
    }

    @Override
    public List<State> getStates() {
        return searchLocalesPort.getStates();
    }

    @Override
    public List<Municipality> getMunicipalitiesByStateID(long stateId) {
        return searchLocalesPort.getMunicipalitiesByStateID(stateId);
    }

    private boolean isValidCep(Cep cep) {
        return cep != null && cep.getCep() != null && !cep.getCep().isBlank() && cep.getLocale() != null;
    }
}
