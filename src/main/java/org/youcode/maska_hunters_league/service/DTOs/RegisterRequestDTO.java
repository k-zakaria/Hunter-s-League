package org.youcode.maska_hunters_league.service.DTOs;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO {
    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String cin;

    private String nationality;

}
