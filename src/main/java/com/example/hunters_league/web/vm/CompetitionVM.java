package com.example.hunters_league.web.vm;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CompetitionVM {
    private String location;
    private LocalDateTime date;
    private Integer currentParticipants;
}

