//package org.youcode.maska_hunters_league.web.rest;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.youcode.maska_hunters_league.service.AuthService;
//import org.youcode.maska_hunters_league.service.DTOs.AuthRequestDTO;
//import org.youcode.maska_hunters_league.service.DTOs.RegesterResponseDTO;
//import org.youcode.maska_hunters_league.service.DTOs.RegisterRequestDTO;
//import org.youcode.maska_hunters_league.service.DTOs.JwtResponseDTO;
//
//@RestController
//@RequestMapping("auth")
//@RequiredArgsConstructor
//public class AuthController {
//    private final AuthService authService;
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponseDTO> AuthenticateAndGetToken(@RequestBody @Valid AuthRequestDTO request) {
//        return ResponseEntity.ok(authService.authenticate(request));
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<RegesterResponseDTO> register(
//            @RequestBody @Valid RegisterRequestDTO request
//    ){
//        return ResponseEntity.ok(authService.register(request));
//    }
//}
//
