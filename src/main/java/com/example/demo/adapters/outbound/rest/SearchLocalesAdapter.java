package com.example.demo.adapters.outbound.rest;

import com.example.demo.application.core.locales.Cep;
import com.example.demo.application.core.locales.Municipality;
import com.example.demo.application.core.locales.State;
import com.example.demo.application.ports.out.SearchLocalesPort;
import com.example.demo.utils.mappers.CepMapper;
import com.example.demo.utils.mappers.MunicipalityMapper;
import com.example.demo.utils.mappers.StateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SearchLocalesAdapter implements SearchLocalesPort {
    private final CepMapper cepMapper;
    private final StateMapper stateMapper;
    private final MunicipalityMapper municipalityMapper;
    private final LocalesApiClient localesApiClient;

    @Override
    public Optional<Cep> searchAdressWithCEP(String cep) {
        return localesApiClient.getAddress(cep)
                .filter(cepDTO -> cepDTO.getCep() != null && !cepDTO.getCep().isBlank())
                .map(cepMapper::DTOtoDomain);
    }

    @Override
    public List<State> getStates() {
        return localesApiClient.getStates().stream().map(stateMapper::DTOtoDomain).toList();
    }

    @Override
    public List<Municipality> getMunicipalitiesByStateID(long stateId) {
        return localesApiClient.getMunicipalitiesByStateID(stateId).stream().map(municipalityMapper::DTOtoDomain).toList();
    }
}
