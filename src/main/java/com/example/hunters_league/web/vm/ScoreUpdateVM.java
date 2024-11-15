package com.example.hunters_league.web.vm;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreUpdateVM {
    private UUID participationId;
    private Double score;
}
