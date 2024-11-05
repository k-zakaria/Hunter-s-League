package com.capps.maskav2.service;

import com.capps.maskav2.domain.User;

public interface AuthService {
    boolean login(User userLogin);
    User register(User user);
}
