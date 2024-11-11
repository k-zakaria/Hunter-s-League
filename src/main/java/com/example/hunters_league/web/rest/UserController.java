package com.example.hunters_league.web.rest;

import com.example.hunters_league.domain.User;
import com.example.hunters_league.service.UserService;
import com.example.hunters_league.service.dto.UserDTO;
import com.example.hunters_league.web.errors.user.UserNotFoundException;
import com.example.hunters_league.web.vm.UserDeleteVM;
import com.example.hunters_league.web.vm.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
        private final UserMapper userMapper;

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody UserDeleteVM userDeleteVM) {
        try {
            boolean isDeleted = userService.delete(userDeleteVM.getUsername());
            if (isDeleted) {
                return ResponseEntity.ok("User deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserDTO> find(@PathVariable String id) {
        User user = userService.findById(id);
        UserDTO userDTO = userMapper.toDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        User savedUser = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDTO(savedUser));
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<UserDTO> findByUser(@PathVariable String username) {
        User user = userService.findByUser(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(userMapper.toDTO(updatedUser));
    }
}