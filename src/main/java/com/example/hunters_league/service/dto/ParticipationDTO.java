package com.example.hunters_league.service.dto;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDTO {

    private UUID id;
    private UUID userId;
    private UUID competitionId;
    private Double score;
}
