package com.example.demo.application.core.service;

import com.example.demo.application.core.locales.Cep;
import com.example.demo.application.core.locales.Municipality;
import com.example.demo.application.core.locales.State;
import com.example.demo.application.exceptions.CepNotFoundException;
import com.example.demo.application.ports.in.LocaleServicePort;
import com.example.demo.application.ports.out.SearchLocalesPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocaleServiceImpl implements LocaleServicePort {
    private final SearchLocalesPort searchLocalesPort;
    private static final Logger log = LoggerFactory.getLogger(LocaleServiceImpl.class);

    public LocaleServiceImpl(SearchLocalesPort searchLocalesPort) {
        this.searchLocalesPort = searchLocalesPort;
    }

    @Override
    public Cep getLocaleByCep(String cep) {
        log.info("executing get locale by CEP: {}", cep);
        Cep locale = searchLocalesPort.searchAddressWithCEP(cep)
                .filter(this::isValidCep)
                .orElseThrow(() -> new CepNotFoundException("No locale found with cep " + cep));
        log.info("locale found: {}", locale.toString());
        return locale;
    }

    @Override
    public List<State> getStates() {
        log.info("executing get all states");
        List<State> states = searchLocalesPort.getStates();
        log.info("states found: {}", states.size());
        return states;
    }

    @Override
    public List<Municipality> getMunicipalitiesByStateID(long stateId) {
        log.info("executing get municipalities by state ID: {}", stateId);
        List<Municipality> municipalities = searchLocalesPort.getMunicipalitiesByStateID(stateId);
        log.info("municipalities found", municipalities.size());
        return municipalities;
    }

    private boolean isValidCep(Cep cep) {
        boolean valid = cep != null && cep.getCep() != null && !cep.getCep().isBlank() && cep.getLocale() != null;
        if (!valid) {
            log.warn("Invalid CEP: {}", cep);
        }
        return valid;
    }
}
