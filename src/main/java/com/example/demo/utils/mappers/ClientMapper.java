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

    @Mapping(source = "adress", target = "adress")
    Client toDomain(JpaClientEntity entity);

    @Mapping(source = "adress", target = "adress")
    JpaClientEntity toEntity(Client client);

    @Mapping(target = "id", ignore = true)
    Client DTOtoDomain(ClientRequestDTO dto);

    @Mapping(source = "adress", target = "adress") // Mapeia o endereço corretamente
    JpaClientEntity DTOtoEntity(ClientRequestDTO dto);

}
