package ru.volkovd.simpleapp.services;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.volkovd.simpleapp.models.User;
import ru.volkovd.simpleapp.security.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Map<Object, Object> createAuthenticationResponse(User user) {
        String email = user.getEmail();
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());
        Map<Object, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("token", token);
        return response;
    }
}
