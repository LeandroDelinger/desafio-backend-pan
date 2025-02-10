package com.example.demo.adapters.inbound;

import com.example.demo.adapters.outbound.repository.ClientRepositoryImpl;
import com.example.demo.application.core.locales.Cep;
import com.example.demo.application.core.locales.Municipality;
import com.example.demo.application.core.locales.State;
import com.example.demo.application.ports.in.LocaleServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locale")
public class LocalesController {
    private final LocaleServicePort localeServicePort;
    private static final Logger log = LoggerFactory.getLogger(LocalesController.class);

    public LocalesController(LocaleServicePort localeServicePort) {
        this.localeServicePort = localeServicePort;
    }

    @GetMapping("/address/{cep}")
    public Cep getClientByCPF(@PathVariable String cep) {
        log.info("Received request to get locale by CEP: {}", cep);
        return localeServicePort.getLocaleByCep(cep);
    }

    @GetMapping("/states")
    public List<State> getStates() {
        log.info("Received request to get all states");
        return localeServicePort.getStates();
    }

    @GetMapping("/municipalities/{stateId}")
    public List<Municipality> getMunicipalitiesByStateID(@PathVariable long stateId) {
        log.info("Received request to get municipalities by state ID: {}", stateId);
        return localeServicePort.getMunicipalitiesByStateID(stateId);
    }
}
