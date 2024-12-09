package org.youcode.maska_hunters_league.service.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.youcode.maska_hunters_league.config.JwtService;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.domain.enums.Role;
import org.youcode.maska_hunters_league.repository.UserRepository;
import org.youcode.maska_hunters_league.service.AuthService;
import org.youcode.maska_hunters_league.service.DTOs.AuthRequestDTO;
import org.youcode.maska_hunters_league.service.DTOs.JwtResponseDTO;
import org.youcode.maska_hunters_league.service.DTOs.RegesterResponseDTO;
import org.youcode.maska_hunters_league.service.DTOs.RegisterRequestDTO;
import org.youcode.maska_hunters_league.web.exception.user.EmailNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public RegesterResponseDTO register(RegisterRequestDTO request) {
        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase()); // Ensure case consistency
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + request.getRole());
        }
        var user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .cin(request.getCin())
                .nationality(request.getNationality())
                .role(role)
                .build();

        userRepository.save(user);

        return RegesterResponseDTO.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .cin(user.getCin())
                .nationality(user.getNationality())
                .role(role.name())
                .build();
    }

    @Override
    public JwtResponseDTO authenticate(AuthRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EmailNotFoundException("User not found"));

        var jwtToken = jwtService.generateToken(user);
        return JwtResponseDTO.builder()
                .accessToken(jwtToken)
                .build();
    }

}
