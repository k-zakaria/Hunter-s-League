package com.example.hunters_league.service;

import com.example.hunters_league.domain.Species;
import com.example.hunters_league.service.dto.SpeciesDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpeciesService {
        boolean delete(UUID id);

        Species findById(UUID id);

        Species saveSpecies(SpeciesDTO speciesDTO);

        Species updateSpecies(UUID id, SpeciesDTO speciesDTO);
}
