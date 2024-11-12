package com.example.hunters_league.web.vm;

import com.example.hunters_league.domain.Competition;
import com.example.hunters_league.domain.Hunt;
import com.example.hunters_league.domain.User;
import lombok.Data;


import java.util.List;
import java.util.UUID;

@Data
public class ParticipationVM {
    private UUID id;
    private UUID userId;
    private UUID competitionId;
    private Double score;
}
