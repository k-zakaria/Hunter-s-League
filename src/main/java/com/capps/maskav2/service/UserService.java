package com.capps.maskav2.service;

import com.capps.maskav2.domain.User;

import java.util.UUID;

public interface UserService {
    User findByUsername(String username);
    boolean deleted(String username);
    
}
