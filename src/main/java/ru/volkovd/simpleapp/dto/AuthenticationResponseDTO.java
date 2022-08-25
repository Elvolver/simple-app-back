package ru.volkovd.simpleapp.dto;

import lombok.Data;

@Data
public class AuthenticationResponseDTO {
    private String email;
    private String token;
}
