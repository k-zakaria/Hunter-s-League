package com.example.hunters_league.service;

import com.example.hunters_league.domain.Participation;
import com.example.hunters_league.service.dto.CompetitionDetailsDTO;
import com.example.hunters_league.service.dto.ParticipationDTO;

import java.util.UUID;

public interface ParticipationService {
    boolean delete(UUID id);

    Participation findById(UUID id);

    Participation save(ParticipationDTO participationDTO);

    Participation update(UUID id, ParticipationDTO participationDTO);

    CompetitionDetailsDTO getCompetitionDetailsByParticipant(UUID participationId);

    Participation updateScore(UUID participationId, Double score);
}
