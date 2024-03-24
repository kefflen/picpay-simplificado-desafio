package com.github.kefflen.picpaysimplificado.wallet;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public record Wallet(
        @Id Long id,
        String fullName,
        String username,
        Long cpf,
        String email,
        String password,
        int type,
        BigDecimal balance
) {
}
