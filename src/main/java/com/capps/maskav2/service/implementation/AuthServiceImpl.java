package com.capps.maskav2.service.implementation;

import com.capps.maskav2.domain.User;
import com.capps.maskav2.repository.UserRepository;
import com.capps.maskav2.service.AuthService;
import com.capps.maskav2.web.rest.excption.IncorrectPasswordException;
import com.capps.maskav2.web.rest.excption.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository authRepository;

    public boolean login(User userLogin){
        User user = authRepository.findByUsername(userLogin.getUsername())
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        if (BCrypt.checkpw(userLogin.getPassword(), user.getPassword())){
            return true;
        }
        throw new IncorrectPasswordException("Incorrect password");
    }
    public User register(User user){
        if (user == null || user.equals(new User())) {
            throw new UserNotFoundException("User not found");
        }
        String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashPassword);
        return authRepository.save(user);
    }
}
