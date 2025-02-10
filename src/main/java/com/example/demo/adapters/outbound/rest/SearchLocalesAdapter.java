package com.example.demo.adapters.outbound.rest;

import com.example.demo.application.core.locales.Cep;
import com.example.demo.application.core.locales.Municipality;
import com.example.demo.application.core.locales.State;
import com.example.demo.application.ports.out.SearchLocalesPort;
import com.example.demo.utils.mappers.CepMapper;
import com.example.demo.utils.mappers.MunicipalityMapper;
import com.example.demo.utils.mappers.StateMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SearchLocalesAdapter implements SearchLocalesPort {
    private static final Logger log = LoggerFactory.getLogger(SearchLocalesAdapter.class);
    private final CepMapper cepMapper;
    private final StateMapper stateMapper;
    private final MunicipalityMapper municipalityMapper;
    private final LocalesApiClient localesApiClient;

    @Override
    public Optional<Cep> searchAddressWithCEP(String cep) {
        log.info("Searching address with CEP: {}", cep);
        return localesApiClient.getAddress(cep)
                .filter(cepDTO -> cepDTO.getCep() != null && !cepDTO.getCep().isBlank())
                .map(cepMapper::DTOtoDomain);
    }

    @Override
    public List<State> getStates() {
        log.info("Fetching list of states.");
        List<State> states = localesApiClient.getStates().stream()
                .map(stateMapper::DTOtoDomain)
                .toList();

        log.info("Fetched {} states.", states.size());
        return states;
    }

    @Override
    public List<Municipality> getMunicipalitiesByStateID(long stateId) {
        log.info("Fetching municipalities for state ID: {}", stateId);
        List<Municipality> municipalities = localesApiClient.getMunicipalitiesByStateID(stateId).stream()
                .map(municipalityMapper::DTOtoDomain)
                .toList();

        log.info("Fetched {} municipalities for state ID: {}", municipalities.size(), stateId);
        return municipalities;
    }
}
