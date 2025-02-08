package com.example.demo.application.ports.in;

import com.example.demo.application.core.locales.Cep;
import com.example.demo.application.core.locales.State;

import java.util.List;

public interface LocaleServicePort {
    public Cep getLocaleByCep(String cep);
    public List<State> getStates();
}
