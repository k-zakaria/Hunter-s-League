package com.capps.maskav2.service.implementation;

import com.capps.maskav2.domain.User;
import com.capps.maskav2.repository.UserRepository;
import com.capps.maskav2.service.UserService;
import com.capps.maskav2.web.rest.excption.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouve avec username : " + username));
    }

    @Override
    public boolean deleted(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non existe avec username : " + username));
        userRepository.delete(user);

        return true;
    }
}
