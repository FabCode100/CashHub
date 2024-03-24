package com.cashub.cashhubbackend.cashub.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users") // Especifica o nome da tabela no banco de dados
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fantasyName", nullable = false)
    private String fantasyName;

    @Column(name = "CNPJ", nullable = false, unique = true)
    private String CNPJ;

    @Column(name = "foundationYear", nullable = false)
    private String foundationYear;

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
}