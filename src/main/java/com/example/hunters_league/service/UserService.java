package com.example.hunters_league.service;

import com.example.hunters_league.domain.User;
import com.example.hunters_league.service.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    boolean delete(String username);
    User findById(String username);
    User updateUser(String id, UserDTO userDTO);
    public User saveUser(UserDTO userDTO);
    Optional<User> findByUser(String username);

}

