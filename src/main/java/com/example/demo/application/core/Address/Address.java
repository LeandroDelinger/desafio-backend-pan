package com.example.demo.application.core.Address;

public class Address {
    private String cep;
    private String street;
    private String number;
    private String complement;
    private String municipality;
    private String city;
    private String state;

    public Address() {}

    public Address(String cep, String street, String number, String complement, String municipality, String city, String state) {
        this.cep = cep;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.municipality = municipality;
        this.city = city;
        this.state = state;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }
}
