package com.cashub.cashhubbackend.cashub.config;

import com.stripe.Stripe;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Setter
@Getter
@Configuration
public class StripeConfig {

    @Value("${stripe.apiKey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        // Verifica se a chave secreta do Stripe foi configurada
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("A chave secreta do Stripe não foi configurada.");
        }
        // Configurando a chave da API do Stripe
        Stripe.apiKey = secretKey;
    }

}
