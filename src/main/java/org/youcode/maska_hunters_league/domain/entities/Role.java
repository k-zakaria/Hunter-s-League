package org.youcode.maska_hunters_league.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

}
