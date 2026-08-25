package com.productcatalog.productcatalog.service;

import com.productcatalog.productcatalog.entity.Role;
import com.productcatalog.productcatalog.entity.User;
import com.productcatalog.productcatalog.repository.RoleRepository;
import com.productcatalog.productcatalog.repository.UserRepository;
import com.productcatalog.productcatalog.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User register(String username, String password, String roleName) {

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() ->
                        new RuntimeException("Role not found: " + roleName));

        User user = new User(
                username,
                passwordEncoder.encode(password),
                role
        );

        return userRepository.save(user);
    }

    public String login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtService.generateToken(
                user.getUsername(),
                user.getRole().getRoleName()
        );
    }
}