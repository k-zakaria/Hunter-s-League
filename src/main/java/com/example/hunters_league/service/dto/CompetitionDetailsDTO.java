package com.example.hunters_league.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CompetitionDetailsDTO {
    private UUID competitionId;
    private String code;
    private String location;
    private LocalDateTime date;
    private Integer minParticipants;
    private Integer maxParticipants;
    private boolean openRegistration;
    private String speciesType;
    private Integer currentParticipants;
}
