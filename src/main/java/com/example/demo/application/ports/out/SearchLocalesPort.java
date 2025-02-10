package com.example.demo.application.ports.out;

import com.example.demo.application.core.locales.Cep;
import com.example.demo.application.core.locales.Municipality;
import com.example.demo.application.core.locales.State;

import java.util.List;
import java.util.Optional;

public interface SearchLocalesPort {
    Optional<Cep> searchAdressWithCEP(String cep);
    List<State> getStates();
    List<Municipality> getMunicipalitiesByStateID(long stateId);
}
