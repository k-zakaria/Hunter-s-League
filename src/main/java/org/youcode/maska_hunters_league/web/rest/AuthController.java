package org.youcode.maska_hunters_league.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.maska_hunters_league.config.JwtService;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.service.AuthService;
import org.youcode.maska_hunters_league.service.DTOs.RegisterRequestDTO;
import org.youcode.maska_hunters_league.web.VMs.AuthVMs.JwtResponseVM;
import org.youcode.maska_hunters_league.web.VMs.AuthVMs.SignInVM;
import org.youcode.maska_hunters_league.web.VMs.AuthVMs.SignUpVM;
import org.youcode.maska_hunters_league.web.VMs.mapper.SignUpVMMapper;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthService authService;
    private final SignUpVMMapper signUpVMMapper;

    @PostMapping("/login")
    public JwtResponseVM AuthenticateAndGetToken(@RequestBody @Valid SignInVM signInVM) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInVM.getEmail(), signInVM.getPassword()));

        if (authentication.isAuthenticated()) {
            return JwtResponseVM.builder()
                    .accessToken(jwtService.generateToken(signInVM.getEmail())).build();
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid SignUpVM signUpVM) {
        RegisterRequestDTO registerRequestDTO = signUpVMMapper.toDTO(signUpVM);
        try {
            User user = authService.register(registerRequestDTO);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            throw new RuntimeException("Email already Exist");
        }
    }
}

