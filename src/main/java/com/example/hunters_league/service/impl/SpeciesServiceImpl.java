package com.example.hunters_league.service.impl;

import com.example.hunters_league.domain.Species;
import com.example.hunters_league.repository.SpeciesRepository;
import com.example.hunters_league.service.SpeciesService;
import com.example.hunters_league.service.dto.SpeciesDTO;
import com.example.hunters_league.web.vm.mapper.SpeciesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public SpeciesDTO save(SpeciesDTO speciesDTO){
        Species species = speciesMapper.toEntity(speciesDTO);
        Species saveSpecies = speciesRepository.save(species);
        return speciesMapper.toDTO(saveSpecies);
    }

    @Override
    public SpeciesDTO update(UUID id, SpeciesDTO speciesDTO){
        Species species = speciesRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal not found"));
        species.setName(speciesDTO.getName());
        species.setCategory(speciesDTO.getCategory());
        species.setMinimumWeight(speciesDTO.getMinimumWeight());
        species.setDifficulty(speciesDTO.getDifficulty());
        species.setPoints(speciesDTO.getPoints());
        Species updateSpecies = speciesRepository.save(species);
        return speciesMapper.toDTO(updateSpecies);
    }

    @Override
    public SpeciesDTO findById(UUID id){
        Species species = speciesRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal not found"));
        return speciesMapper.toDTO(species);
    }


    public void deleted(UUID id){
        speciesRepository.deleteById(id);
    }

}
