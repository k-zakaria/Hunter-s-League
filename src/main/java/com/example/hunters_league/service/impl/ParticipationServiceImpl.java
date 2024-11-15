package com.example.hunters_league.service.impl;

import com.example.hunters_league.service.ParticipationService;
import com.example.hunters_league.domain.Participation;
import com.example.hunters_league.repository.ParticipationRepository;
import com.example.hunters_league.service.dto.CompetitionDetailsDTO;
import com.example.hunters_league.service.dto.ParticipationDTO;
import com.example.hunters_league.web.errors.participation.ParticipationNotFoundException;
import com.example.hunters_league.web.vm.mapper.CompetitionMapper;
import com.example.hunters_league.web.vm.mapper.ParticipationMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;
    private final ParticipationMapper participationMapper;
    private final CompetitionMapper competitionMapper;

    @Override
    public boolean delete(UUID id) {
        Participation participation = participationRepository.findById(id)
                .orElseThrow(() -> new ParticipationNotFoundException("Participation not found"));
        participationRepository.delete(participation);
        return true;
    }

    @Override
    public Participation findById(UUID id) {
        return participationRepository.findById(id)
                .orElseThrow(() -> new ParticipationNotFoundException("Participation not found"));
    }

    @Override
    public Participation save(ParticipationDTO participationDTO) {
        Participation participation = participationMapper.toEntity(participationDTO);
        return participationRepository.save(participation);
    }

    @Override
    public Participation update(UUID id, ParticipationDTO participationDTO) {
        Participation participation = participationRepository.findById(id)
                .orElseThrow(() -> new ParticipationNotFoundException("Participation not found"));
        participation.setScore(participationDTO.getScore());
        participation.setUser(participation.getUser());
        participation.setCompetition(participation.getCompetition());

        return participationRepository.save(participation);
    }

    @Override
    public CompetitionDetailsDTO getCompetitionDetailsByParticipant(UUID participationId) {
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> new ParticipationNotFoundException("Participation not found"));
        return competitionMapper.toDetailsDTO(participation.getCompetition());
    }

    @Override
    @Transactional
    public Participation updateScore(UUID participationId, Double score) {
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> new ParticipationNotFoundException("Participation not found"));

        participation.setScore(score);
        return participationRepository.save(participation);
    }

}
