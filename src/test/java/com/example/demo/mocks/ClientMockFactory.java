package com.example.demo.mocks;

import com.example.demo.adapters.inbound.request.AddressRequestDTO;
import com.example.demo.adapters.inbound.request.ClientRequestDTO;
import com.example.demo.adapters.outbound.entities.JpaAddressEntity;
import com.example.demo.adapters.outbound.entities.JpaClientEntity;
import com.example.demo.application.core.Address.Address;
import com.example.demo.application.core.client.Client;

import java.time.LocalDate;
import java.util.UUID;

public class ClientMockFactory {

    public static ClientRequestDTO createClientRequest() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO();
        clientRequestDTO.setName("John Doe");
        clientRequestDTO.setCpf("12345678900");
        clientRequestDTO.setAddress(createAddressRequest());
        return clientRequestDTO;
    }

    public static Client createClient() {
        ClientRequestDTO clientRequestDTO = createClientRequest();
        Client client = new Client();
        client.setId(UUID.randomUUID());
        client.setName(clientRequestDTO.getName());
        client.setCpf(clientRequestDTO.getCpf());
        client.setDateOfBirth(clientRequestDTO.getDateOfBirth());
        client.setAddress(createAddress());
        return client;
    }

    public static Address createAddress() {
        AddressRequestDTO addressRequestDTO = createAddressRequest();
        Address address = new Address();
        address.setStreet(addressRequestDTO.getStreet());
        address.setCep(addressRequestDTO.getCep());
        address.setCity(addressRequestDTO.getCity());
        address.setState(addressRequestDTO.getState());
        address.setMunicipality(addressRequestDTO.getMunicipality());
        address.setNumber(addressRequestDTO.getNumber());
        return address;
    }

    public static AddressRequestDTO createAddressRequest() {
        AddressRequestDTO addressRequestDTO = new AddressRequestDTO();
        addressRequestDTO.setStreet("Street 1");
        addressRequestDTO.setCep("12345678");
        addressRequestDTO.setCity("São Paulo");
        addressRequestDTO.setState("SP");
        addressRequestDTO.setMunicipality("São Paulo");
        addressRequestDTO.setNumber("123");
        return addressRequestDTO;
    }

    public static JpaClientEntity createJpaClientEntity() {
        JpaClientEntity jpaClientEntity = new JpaClientEntity();
        jpaClientEntity.setId(UUID.randomUUID());
        jpaClientEntity.setName("John Doe");
        jpaClientEntity.setCpf("12345678900");
        jpaClientEntity.setDateOfBirth(LocalDate.of(1990, 1, 1));
        jpaClientEntity.setAddress(createJpaAddressEntity());
        return jpaClientEntity;
    }

    public static JpaAddressEntity createJpaAddressEntity() {
        JpaAddressEntity jpaAddressEntity = new JpaAddressEntity();
        long id = UUID.randomUUID().getMostSignificantBits();
        jpaAddressEntity.setId(id);
        jpaAddressEntity.setStreet("Street 1");
        jpaAddressEntity.setCep("12345678");
        jpaAddressEntity.setCity("São Paulo");
        jpaAddressEntity.setState("SP");
        jpaAddressEntity.setMunicipality("São Paulo");
        jpaAddressEntity.setNumber("123");
        return jpaAddressEntity;
    }
}
