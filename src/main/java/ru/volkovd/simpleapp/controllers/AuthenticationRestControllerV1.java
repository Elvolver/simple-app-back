package ru.volkovd.simpleapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import ru.volkovd.simpleapp.dto.AuthenticationRequestDTO;
import ru.volkovd.simpleapp.dto.RegistrationRequestDTO;
import ru.volkovd.simpleapp.models.User;
import ru.volkovd.simpleapp.models.UserProfile;
import ru.volkovd.simpleapp.repository.UserRepository;
import ru.volkovd.simpleapp.security.JwtTokenProvider;
import ru.volkovd.simpleapp.security.UserDetailsServiceImpl;
import ru.volkovd.simpleapp.services.AuthenticationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthenticationRestControllerV1 {

    private final UserDetailsServiceImpl userService;
    private final AuthenticationService authenticationService; 

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationRestControllerV1(UserDetailsServiceImpl userService, AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        Map<Object, Object> response = authenticationService.createAuthenticationResponse(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logout")
    public void authenticate(HttpServletRequest request, HttpServletResponse response) {
        String x = request.getUserPrincipal().getName();
        User user = userService.getUserByUserName(x);

        log.info("Logout user: {}", user);

        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequestDTO request) {
        User user = userService.save(request);
        Map<Object, Object> response = authenticationService.createAuthenticationResponse(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/checkLogin")
    public ResponseEntity<?> checkLogin(@RequestBody AuthenticationRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        Map<Object, Object> response  = new HashMap<>();
        if (user == null) {
            response.put("result", false);
        } else {
            response.put("result", true);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(HttpServletRequest request) {
        String x = request.getUserPrincipal().getName();
        User user = userService.getUserByUserName(x);
        Map<Object, Object> response = authenticationService.createAuthenticationResponse(user);
        return ResponseEntity.ok(response);
    }
}
