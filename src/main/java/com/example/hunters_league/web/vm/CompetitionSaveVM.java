package com.example.hunters_league.web.vm;

import com.example.hunters_league.domain.enums.SpeciesType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompetitionSaveVM {
    private String code;
    private String location;
    private LocalDateTime date;
    private SpeciesType speciesType;
    private Integer minParticipants;
    private Integer maxParticipants;
    private Boolean openRegistration;
}