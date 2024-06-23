package com.cashub.cashhubbackend.cashub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.jetbrains.annotations.NotNull;
import java.time.LocalDateTime;

public record TransactionDTO(
        Long id,
        @NotNull Long accountId,
        Long categoryId,
        @NotNull Double amount,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime transactionDate,
        String description
) {}
