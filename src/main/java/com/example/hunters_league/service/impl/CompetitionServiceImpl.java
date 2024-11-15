package com.example.hunters_league.service.impl;

import com.example.hunters_league.domain.Competition;
import com.example.hunters_league.domain.Participation;
import com.example.hunters_league.domain.User;
import com.example.hunters_league.repository.CompetitionRepository;
import com.example.hunters_league.repository.ParticipationRepository;
import com.example.hunters_league.service.CompetitionService;
import com.example.hunters_league.service.dto.CompetitionDetailsDTO;
import com.example.hunters_league.web.errors.competition.CompetitionNotFoundException;
import com.example.hunters_league.web.errors.participation.ParticipationNotFoundException;
import com.example.hunters_league.web.vm.mapper.CompetitionMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;



    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition findById(String id) {
        return competitionRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new CompetitionNotFoundException("Competition not found"));
    }

    @Override
    public Competition save(Competition competition){
        return competitionRepository.save(competition);
    }
}