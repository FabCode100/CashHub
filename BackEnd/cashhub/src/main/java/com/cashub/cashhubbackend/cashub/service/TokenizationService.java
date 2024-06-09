package com.cashub.cashhubbackend.cashub.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenizationService {

    private final Map<String, String> tokenStore = new HashMap<>();
    private final SecureRandom secureRandom = new SecureRandom();

    public String tokenize(String data) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, data);
        return token;
    }

    public String detokenize(String token) {
        return tokenStore.get(token);
    }
}
