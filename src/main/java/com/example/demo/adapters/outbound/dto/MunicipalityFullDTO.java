package com.example.demo.adapters.outbound.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MunicipalityFullDTO {
    private Long id;
    private String nome;
    private MicrorregiaoDTO microrregiao;

    @Data
    public static class MicrorregiaoDTO {
        private Long id;
        private String nome;
        private MesorregiaoDTO mesorregiao;
    }

    @Data
    public static class MesorregiaoDTO {
        private Long id;
        private String nome;
        @JsonProperty("UF")  // 🔥 Essa anotação força a desserialização correta
        private UFDTO uf;
    }

    @Data
    public static class UFDTO {
        private Long id;
        private String sigla;
        private String nome;
        private RegiaoDTO regiao;
    }

    @Data
    public static class RegiaoDTO {
        private Long id;
        private String sigla;
        private String nome;
    }
}

