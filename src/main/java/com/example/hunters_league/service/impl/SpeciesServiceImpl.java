package com.example.hunters_league.service.impl;

import com.example.hunters_league.domain.Species;
import com.example.hunters_league.repository.SpeciesRepository;
import com.example.hunters_league.service.SpeciesService;
import com.example.hunters_league.service.dto.SpeciesDTO;
import com.example.hunters_league.web.errors.species.SpeciesNotFoundException;
import com.example.hunters_league.web.vm.mapper.SpeciesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import java.util.UUID;

@Service
public class SpeciesServiceImpl implements SpeciesService {

    private final SpeciesRepository speciesRepository;
    private final SpeciesMapper speciesMapper;

    @Autowired
    public SpeciesServiceImpl(SpeciesRepository speciesRepository, SpeciesMapper speciesMapper) {
        this.speciesRepository = speciesRepository;
        this.speciesMapper = speciesMapper;
    }

    @Override
    public boolean delete(UUID id) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new SpeciesNotFoundException("Species not found"));
        speciesRepository.delete(species);
        return true;
    }

    @Override
    public Species findById(UUID id) {
        return speciesRepository.findById(id)
                .orElseThrow(() -> new SpeciesNotFoundException("Species not found"));
    }

    @Override
    public Species saveSpecies(SpeciesDTO speciesDTO) {
        Species species = speciesMapper.toEntity(speciesDTO);
        return speciesRepository.save(species);
    }


    @Override
    public Species updateSpecies(UUID id, SpeciesDTO speciesDTO) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new SpeciesNotFoundException("Species not found"));

        species.setName(speciesDTO.getName());
        species.setCategory(speciesDTO.getCategory());
        species.setMinimumWeight(speciesDTO.getMinimumWeight());
        species.setDifficulty(speciesDTO.getDifficulty());
        species.setPoints(speciesDTO.getPoints());

        return speciesRepository.save(species);
    }

    @Override
    public Page<Species> getAllSpecies(Pageable pageable) {
        return speciesRepository.findAll(pageable);
    }


}
