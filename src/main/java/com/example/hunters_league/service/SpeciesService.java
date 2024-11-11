package com.example.hunters_league.service;

import com.example.hunters_league.service.dto.SpeciesDTO;

import java.util.List;
import java.util.UUID;

public interface SpeciesService {
        SpeciesDTO save(SpeciesDTO speciesDTO);
        SpeciesDTO update(UUID id, SpeciesDTO speciesDTO);
        SpeciesDTO findById(UUID id);
        void deleted(UUID id);
}
