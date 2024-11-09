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
            // Log et retour d'une réponse 404 pour l'utilisateur non trouvé
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            // Log complet de toute autre exception pour diagnostiquer
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
}