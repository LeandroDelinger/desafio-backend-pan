package com.example.demo.mocks;

import com.example.demo.adapters.outbound.dto.CepResponseDTO;
import com.example.demo.adapters.outbound.dto.MunicipalityFullDTO;
import com.example.demo.adapters.outbound.dto.StateResponseDTO;
import com.example.demo.application.core.locales.Cep;
import com.example.demo.application.core.locales.Municipality;
import com.example.demo.application.core.locales.State;

public class LocalesMockFactory {
    public static Cep createCep() {
        Cep newCep = new Cep();
        newCep.setCep("04856110");
        newCep.setPatio("Street Name");
        newCep.setCity("São Paulo");
        newCep.setState("SP");
        newCep.setLocale("Locale");
        newCep.setRegion("Region");
        newCep.setNeighborhood("Neighborhood");
        newCep.setComplement("Complement");
        newCep.setUnit("Unit");
        return newCep;
    }


    public static State createState() {
        State newState = new State();
        newState.setId(35);
        newState.setName("São Paulo");
        newState.setAcronym("SP");
        return newState;
    }

    public static Municipality createMunicipality() {
        Municipality newMunicipality = new Municipality();
        newMunicipality.setName("São Paulo");
        newMunicipality.setState("SP");
        newMunicipality.setRegion("Suldeste");
        return newMunicipality;
    }

    public static CepResponseDTO cepResponseDTO() {
        CepResponseDTO cepResponseDTO = new CepResponseDTO();
        cepResponseDTO.setCep("04856110");
        cepResponseDTO.setLogradouro("Street Name");
        cepResponseDTO.setLocalidade("São Paulo");
        cepResponseDTO.setUf("SP");
        cepResponseDTO.setBairro("Neighborhood");
        cepResponseDTO.setComplemento("Complement");
        cepResponseDTO.setUnidade("Unit");
        cepResponseDTO.setRegiao("Region");
        return cepResponseDTO;
    }

    public static StateResponseDTO stateResponseDTO() {
        StateResponseDTO stateResponseDTO = new StateResponseDTO();
        stateResponseDTO.setId(35);
        stateResponseDTO.setNome("São Paulo");
        stateResponseDTO.setSigla("SP");
        return stateResponseDTO;
    }

    public static MunicipalityFullDTO municipalityFullDTO() {
        MunicipalityFullDTO municipalityFullDTO = new MunicipalityFullDTO();
        municipalityFullDTO.setId(1L);
        municipalityFullDTO.setNome("São Paulo");

        return municipalityFullDTO;
    }
}
