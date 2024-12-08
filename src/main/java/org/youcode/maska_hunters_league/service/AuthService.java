package org.youcode.maska_hunters_league.service;

import org.youcode.maska_hunters_league.domain.entities.AppRole;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.service.DTOs.RegisterRequestDTO;

public interface AuthService {
    User register (RegisterRequestDTO registerRequestDTO) throws Exception;
    AppRole getRoleByName (String role_name );
}
