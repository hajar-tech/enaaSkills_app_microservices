package com.enaaskills.authservice.dtos;

public record LoginResponse(
        String token , Long userId , String role, String email
) {
}
