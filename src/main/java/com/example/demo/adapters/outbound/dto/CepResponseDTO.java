package com.example.demo.adapters.outbound.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CepResponseDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
}
