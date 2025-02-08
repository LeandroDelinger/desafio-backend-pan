package com.example.demo.application.ports.out;

import com.example.demo.application.core.locales.Cep;

public interface SearchLocalesPort {
    Cep searchAdressWithCEP(String cep);
}
