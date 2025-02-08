package com.example.demo.utils.mappers;


import com.example.demo.adapters.outbound.dto.StateResponseDTO;
import com.example.demo.application.core.locales.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface StateMapper {
    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "acronym", source = "dto.sigla")
    @Mapping(target = "name", source = "dto.nome")
    State DTOtoDomain(StateResponseDTO dto);
}
