package ru.volkovd.simpleapp.services;

import ru.volkovd.simpleapp.models.User;

import java.util.Map;

public interface AuthenticationService {
    Map<Object, Object> createAuthenticationResponse(User user);
}
