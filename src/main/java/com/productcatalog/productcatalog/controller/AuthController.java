package com.productcatalog.productcatalog.controller;

import com.productcatalog.productcatalog.entity.User;
import com.productcatalog.productcatalog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String roleName) {

        User user = authService.register(username, password, roleName);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestParam String username,
            @RequestParam String password) {

        String token = authService.login(username, password);

        return ResponseEntity.ok(
                new LoginResponse(username, token)
        );
    }

    public static class LoginResponse {

        private String username;
        private String token;

        public LoginResponse(String username, String token) {
            this.username = username;
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public String getToken() {
            return token;
        }
    }
}