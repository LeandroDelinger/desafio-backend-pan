package com.example.demo.adapters.outbound.rest;

import com.example.demo.adapters.outbound.dto.CepResponseDTO;
import com.example.demo.adapters.outbound.dto.StateResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class LocalesApiClient {
    private final RestTemplate restTemplate;

    public LocalesApiClient() {
        this.restTemplate = new RestTemplate();
    }

    public Optional<CepResponseDTO> getAddress(String cep) {
        String url = String.format("https://viacep.com.br/ws/%s/json", cep);
        return Optional.of(restTemplate.getForObject(url, CepResponseDTO.class));
    }

    public List<StateResponseDTO> getStates() {
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/";
        StateResponseDTO[] response = restTemplate.getForObject(url, StateResponseDTO[].class);
        return List.of(response);
    }
}
