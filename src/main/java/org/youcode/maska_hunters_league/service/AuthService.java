package org.youcode.maska_hunters_league.service;

import org.youcode.maska_hunters_league.service.DTOs.AuthRequestDTO;
import org.youcode.maska_hunters_league.service.DTOs.JwtResponseDTO;
import org.youcode.maska_hunters_league.service.DTOs.RegesterResponseDTO;
import org.youcode.maska_hunters_league.service.DTOs.RegisterRequestDTO;

public interface AuthService {
    RegesterResponseDTO register(RegisterRequestDTO request);
    JwtResponseDTO authenticate(AuthRequestDTO request);
}
