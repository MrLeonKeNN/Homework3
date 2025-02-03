package aston.intensive.authentication_server.controller.impl;

import aston.intensive.authentication_server.jwt.JwtTokenProvider;
import aston.intensive.authentication_server.service.api.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationControllerImpl {

    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ResponseEntity<String> login() {
        return ResponseEntity.ok(jwtTokenProvider.generateToken("SADSA", new ArrayList<>()));
    }
}
