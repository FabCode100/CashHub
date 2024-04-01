package com.cashub.cashhubbackend.cashub.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "enterprises") // Especifica o nome da tabela no banco de dados
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fantasyName", nullable = false)
    private String fantasyName;

    @Column(name = "CNPJ", nullable = false, unique = true)
    private String CNPJ;

    @Column(name = "foundationYear", nullable = false)
    private Date foundationYear;

    @Column(name = "taxRegime", nullable = false)
    private String taxRegime;

    @Column(name = "CEP", nullable = false)
    private String CEP;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "complement", nullable = false)
    private String complement;

    @Column(name = "comercialNumber", nullable = false, unique = true)
    private String comercialNumber;

    @Column(name = "businessMail", nullable = false, unique = true)
    private String businessMail;

    public Enterprise() {
    }

    public Enterprise(String fantasyName, String CNPJ, Date foundationYear, String taxRegime, String CEP, String country, String state, String city, String complement ,String comercialNumber,String businessMail) {
        this.fantasyName = fantasyName;
        this.CNPJ = CNPJ;
        this.foundationYear = foundationYear;
        this.taxRegime = taxRegime;
        this.CEP = CEP;
        this.country = country;
        this.state = state;
        this.city = city;
        this.complement = complement;
        this.comercialNumber = comercialNumber;
        this.businessMail = businessMail;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFirstName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public Date getFoundatioYear() {
        return foundationYear;
    }

    public void setFoundationYear(Date foundationYear) {
        this.foundationYear = foundationYear;
    }

    public String getTaxRegime() {
        return taxRegime;
    }

    public void setTaxRegime(String taxRegime) {
        this.taxRegime = taxRegime;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getComercialNumber() {
        return comercialNumber;
    }

    public void setComercialNumber(String comercialNumber) {
        this.comercialNumber = comercialNumber;
    }

    public String getBusinessMail() {
        return businessMail;
    }

    public void setBusinessMail(String businessMail) {
        this.businessMail = businessMail;
    }

}

