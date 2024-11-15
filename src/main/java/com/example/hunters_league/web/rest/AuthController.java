package com.example.hunters_league.web.rest;

import com.example.hunters_league.domain.User;
import com.example.hunters_league.service.AuthService;
import com.example.hunters_league.service.dto.UserDTO;
import com.example.hunters_league.web.vm.UserLoginVM;
import com.example.hunters_league.web.vm.UserRegisterVM;
import com.example.hunters_league.web.vm.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {


    private final UserMapper userMapper;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginVM userLoginVM) {

        User user = userMapper.toEntity(userLoginVM);
        boolean islogedIn = authService.login(user);
        if(islogedIn) {
            return ResponseEntity.ok("Login Success");
        } else {
            return  ResponseEntity.ok("Login faild");
        }

    }
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserRegisterVM userRegisterVM) {

        User user = userMapper.toEntity(userRegisterVM);
        User savedUser = authService.register(user);
        UserDTO userDTO = userMapper.toDTO(savedUser);
        return new ResponseEntity<>(userDTO , HttpStatus.CREATED);

    }

}
