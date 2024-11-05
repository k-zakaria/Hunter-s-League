package com.capps.maskav2.web.rest.controller;

import com.capps.maskav2.domain.User;
import com.capps.maskav2.service.AuthService;
import com.capps.maskav2.service.dto.UserDTO;
import com.capps.maskav2.web.vm.UserLoginVM;
import com.capps.maskav2.web.vm.UserRegisterVM;
import com.capps.maskav2.web.vm.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
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

    private final AuthService authService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginVM userLoginVM){
        User user = userMapper.toEntity(userLoginVM);
        boolean isloged = authService.login(user);
        if (isloged){
            return ResponseEntity.ok("login success");
        }
        return ResponseEntity.ok("login faild");
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(UserRegisterVM userRegisterVM){
        User user = userMapper.toEntity(userRegisterVM);
        User saved = authService.register(user);

        if (saved != null) {
            UserDTO userDTO = userMapper.toDTO(saved);
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
