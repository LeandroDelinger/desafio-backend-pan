package com.example.demo.adapters.outbound.rest;

import com.example.demo.adapters.outbound.dto.CepResponseDTO;
import com.example.demo.adapters.outbound.dto.MunicipalityFullDTO;
import com.example.demo.adapters.outbound.dto.StateResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class LocalesApiClient {
    private final RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(LocalesApiClient.class);

    @Value("${api.viacep.url}")
    private String viaCepUrl;

    @Value("${api.ibge.states.url}")
    private String ibgeStatesUrl;

    @Value("${api.ibge.municipalities.url}")
    private String ibgeMunicipalitiesUrl;

    @Autowired
    public LocalesApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<CepResponseDTO> getAddress(String cep) {
        String url = String.format("%s%s/json", viaCepUrl, cep);
        try {
            CepResponseDTO response = restTemplate.getForObject(url, CepResponseDTO.class);
            return Optional.of(response);
        } catch (Exception e) {
            log.error("Error occurred while fetching address for CEP: {}", cep, e);
            return Optional.empty();
        }
    }

    public List<StateResponseDTO> getStates() {
        try {
            StateResponseDTO[] response = restTemplate.getForObject(ibgeStatesUrl, StateResponseDTO[].class);
            return List.of(response);
        } catch (Exception e) {
            log.error("Error occurred while fetching states", e);
            return List.of();
        }

    }

    public List<MunicipalityFullDTO> getMunicipalitiesByStateID(long stateId) {
        String url = ibgeMunicipalitiesUrl.replace("{stateId}", String.valueOf(stateId));
        try {
            MunicipalityFullDTO[] response = restTemplate.getForObject(url, MunicipalityFullDTO[].class);
            return List.of(response);
        } catch (Exception e) {
            log.error("Error occurred while fetching municipalities for state ID: {}", stateId, e);
            return List.of();
        }

    }
}
