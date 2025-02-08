package com.example.demo.adapters.inbound;

import com.example.demo.application.core.locales.Cep;
import com.example.demo.application.core.locales.State;
import com.example.demo.application.ports.in.LocaleServicePort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locale")
public class LocalesController {
    private final LocaleServicePort localeServicePort;

    public LocalesController(LocaleServicePort localeServicePort) {
        this.localeServicePort = localeServicePort;
    }

    @GetMapping("/address/{cep}")
    public Cep getClientByCPF(@PathVariable String cep) {
        return localeServicePort.getLocaleByCep(cep);
    }

    @GetMapping("/states")
    public List<State> getStates() {
        return localeServicePort.getStates();
    }
}
