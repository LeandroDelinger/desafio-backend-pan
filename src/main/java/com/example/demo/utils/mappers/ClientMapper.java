package com.example.demo.utils.mappers;

import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.adapters.outbound.entities.JpaClientEntity;
import com.example.demo.application.core.client.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

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

}
