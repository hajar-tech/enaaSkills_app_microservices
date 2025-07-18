package com.enaaskills.authservice.dtos;

public record LoginRequest(
        String email, String password
) {
}
