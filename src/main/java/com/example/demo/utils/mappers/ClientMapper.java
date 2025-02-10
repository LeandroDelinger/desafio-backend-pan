package com.example.demo.utils.mappers;

import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.adapters.inbound.response.ClientResponseDTO;
import com.example.demo.adapters.outbound.entities.JpaClientEntity;
import com.example.demo.application.core.client.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
@Component
public interface ClientMapper {

    @Mapping(source = "address", target = "address")
    Client toDomain(JpaClientEntity entity);

    @Mapping(source = "address", target = "address")
    JpaClientEntity toEntity(Client client);

    @Mapping(target = "id", ignore = true)
    Client DTOtoDomain(ClientRequestDTO dto);

    @Mapping(source = "address", target = "address") // Mapeia o endereço corretamente
    JpaClientEntity DTOtoEntity(ClientRequestDTO dto);

    @Mapping(source = "dateOfBirth", target = "dateOfBirth", qualifiedByName = "mapDateToString")
    ClientResponseDTO toResponseDTO(Client client);

    @Named("mapDateToString")
    default String mapDateToString(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ISO_LOCAL_DATE) : null;
    }

}
