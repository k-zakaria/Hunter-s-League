package org.youcode.maska_hunters_league.service.Implementations;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.youcode.maska_hunters_league.domain.entities.AppRole;
import org.youcode.maska_hunters_league.domain.entities.User;
import org.youcode.maska_hunters_league.repository.AppRoleRepository;
import org.youcode.maska_hunters_league.repository.UserRepository;
import org.youcode.maska_hunters_league.service.AuthService;
import org.youcode.maska_hunters_league.service.DTOs.RegisterRequestDTO;
import org.youcode.maska_hunters_league.web.exception.user.EmailAlreadyExistException;
import org.youcode.maska_hunters_league.web.exception.InvalidCredentialsException;
import org.youcode.maska_hunters_league.web.exception.user.InvalidUserExeption;
import org.youcode.maska_hunters_league.web.exception.user.UserNameAlreadyExistException;

import java.util.HashSet;

@Service

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AppRoleRepository roleRepository;

    public AuthServiceImpl(UserRepository userRepository, AppRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
//    @Override
//    public User registerUser(User user) {
//            if(user == null) {
//                throw new InvalidUserExeption("user is null");
//            }
//
//            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
//                throw new UserNameAlreadyExistException();
//            }
//
//            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//                throw new EmailAlreadyExistException();
//            }
//            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
//            return userRepository.save(user);
//        }
//
//        @Override
//    public Boolean login(User user) {
//            if (user == null) {
//                throw new InvalidUserExeption("user is null");
//            }
//            User existingUser = userRepository.findByUsername(user.getUsername())
//                    .orElseThrow(() -> new InvalidCredentialsException("username or password is incorrect"));
//
//            if (BCrypt.checkpw(user.getPassword(), existingUser.getPassword())) {
//                return true;
//            } else {
//                throw new InvalidCredentialsException("username or password is incorrect");
//            }
//    }

    @Override
    public User register(RegisterRequestDTO registerRequestDTO) throws Exception {
        validateUserRegistration(registerRequestDTO);

        if (userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setLastName(registerRequestDTO.getLastName());
        user.setFirstName(registerRequestDTO.getFirstName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setCin(registerRequestDTO.getCin());
        user.setNationality(registerRequestDTO.getNationality());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(registerRequestDTO.getPassword());
        user.setPassword(encodedPassword);

        AppRole role = getRoleByName("ROLE_USER");
        System.out.println("Rôle récupéré ou créé : " + role);

        user.getRoles().add(role);
        System.out.println("Rôles associés à l'utilisateur : " + user.getRoles());

        return userRepository.save(user);
    }


    @Override
    public AppRole getRoleByName(String role_name) {
        roleRepository.getRoleByName(role_name);
        AppRole newRole = new AppRole();
        newRole.setName(role_name);
        return roleRepository.save(newRole);
    }




    private void validateUserRegistration(RegisterRequestDTO user) {
        if (user == null) {
            throw new InvalidUserExeption("User cannot be null");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new InvalidUserExeption("Username is required");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new InvalidUserExeption("Email is required");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new InvalidUserExeption("Password is required");
        }
        if (user.getCin() == null || user.getCin().trim().isEmpty()) {
            throw new InvalidUserExeption("User cannot be null");
        }
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            throw new InvalidUserExeption("first name is required");
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            throw new InvalidUserExeption("Last name is required");
        }
        if (user.getNationality() == null || user.getNationality().trim().isEmpty()) {
            throw new InvalidUserExeption("Nationality is required");
        }
    }



}
