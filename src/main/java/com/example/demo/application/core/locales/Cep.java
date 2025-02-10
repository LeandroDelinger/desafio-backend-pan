package com.example.demo.application.core.locales;

public class Cep {
    private String cep;
    private String patio;
    private String complement;
    private String unit;
    private String neighborhood;
    private String locale;
    private String city;
    private String state;
    private String region;

    public Cep() {}

    public Cep(String cep, String patio, String complement, String unit, String neighborhood, String locale, String city, String state, String region) {
        this.cep = cep;
        this.patio = patio;
        this.complement = complement;
        this.unit = unit;
        this.neighborhood = neighborhood;
        this.locale = locale;
        this.city = city;
        this.state = state;
        this.region = region;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPatio() {
        return patio;
    }

    public void setPatio(String patio) {
        this.patio = patio;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Cep{" +
                "cep='" + cep + '\'' +
                ", patio='" + patio + '\'' +
                ", complement='" + complement + '\'' +
                ", unit='" + unit + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", locale='" + locale + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}

