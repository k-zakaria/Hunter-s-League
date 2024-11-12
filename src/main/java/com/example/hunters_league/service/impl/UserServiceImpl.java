package com.example.hunters_league.service.impl;

import com.example.hunters_league.domain.User;
import com.example.hunters_league.repository.UserRepository;
import com.example.hunters_league.service.UserService;
import com.example.hunters_league.service.dto.UserDTO;
import com.example.hunters_league.web.errors.user.UserNotFoundException;
import com.example.hunters_league.web.vm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public boolean delete(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        userRepository.delete(user);
        return true;
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    @Override
    public User saveUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User updateUser(String id, UserDTO userDTO) {
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCin(userDTO.getCin());

        return userRepository.save(user);
    }
}