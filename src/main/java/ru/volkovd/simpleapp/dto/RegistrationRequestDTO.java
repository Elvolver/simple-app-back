package ru.volkovd.simpleapp.dto;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class RegistrationRequestDTO {
    private String email;
    private String password;
    private String firstname;
    private String lastname;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String getPassword() {

        return encoder.encode(password);
    }
}
