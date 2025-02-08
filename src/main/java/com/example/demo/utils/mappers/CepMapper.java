package com.example.demo.utils.mappers;

import com.example.demo.adapters.outbound.dto.CepResponseDTO;
import com.example.demo.application.core.locales.Cep;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CepMapper {
    @Mapping(target = "cep", source = "cep")
    @Mapping(target = "patio", source = "logradouro")
    @Mapping(target = "complement", source = "complemento")
    @Mapping(target = "unit", source = "unidade")
    @Mapping(target = "neighborhood", source = "bairro")
    @Mapping(target = "locale", source = "localidade")
    @Mapping(target = "city", source = "uf")
    @Mapping(target = "state", source = "estado")
    @Mapping(target = "region", source = "regiao")
    Cep DTOtoDomain(CepResponseDTO dto);
}
