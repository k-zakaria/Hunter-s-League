package com.example.hunters_league.web.vm.mapper;

import com.example.hunters_league.domain.Species;
import com.example.hunters_league.service.dto.SpeciesDTO;
import com.example.hunters_league.web.vm.SpeciesVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpeciesMapper {
    Species toEntity(SpeciesVM speciesVM);
    SpeciesDTO toDTO(Species species);
    SpeciesVM toVM(Species species);
    Species toEntity(SpeciesDTO speciesDTO);



}