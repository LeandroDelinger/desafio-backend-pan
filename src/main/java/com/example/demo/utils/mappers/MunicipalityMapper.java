package com.example.demo.utils.mappers;

import com.example.demo.adapters.outbound.dto.MunicipalityFullDTO;
import com.example.demo.application.core.locales.Municipality;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MunicipalityMapper {

    @Mapping(target = "name", source = "nome")
    @Mapping(target = "state", source = "microrregiao.mesorregiao.uf.nome")
    @Mapping(target = "region", source = "microrregiao.mesorregiao.uf.regiao.nome")
    Municipality DTOtoDomain(MunicipalityFullDTO dto);
}
