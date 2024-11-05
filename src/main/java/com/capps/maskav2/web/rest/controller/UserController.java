package com.capps.maskav2.web.rest.controller;

import com.capps.maskav2.domain.User;
import com.capps.maskav2.service.UserService;
import com.capps.maskav2.service.dto.UserDTO;
import com.capps.maskav2.web.vm.UserDeleteVM;
import com.capps.maskav2.web.vm.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/find/{username}")
    public ResponseEntity<UserDTO> findByUsername(@RequestBody String username){
        User user = userService.findByUsername(username);
        UserDTO userDTO = userMapper.toDTO(user);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping("/delete/{username}")
    public ResponseEntity<String> delete(@RequestBody UserDeleteVM userDeleteVM){
        boolean isDeleted = userService.deleted(userDeleteVM.getUsername());
        if (isDeleted){
            return ResponseEntity.ok("User deleted");
        }
        return ResponseEntity.ok("User not found");
    }
}
