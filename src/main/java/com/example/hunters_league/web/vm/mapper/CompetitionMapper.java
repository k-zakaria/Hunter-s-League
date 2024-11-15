package com.example.hunters_league.web.vm.mapper;

import com.example.hunters_league.domain.Competition;
import com.example.hunters_league.service.dto.CompetitionDTO;
import com.example.hunters_league.service.dto.CompetitionDetailsDTO;
import com.example.hunters_league.web.vm.CompetitionSaveVM;
import com.example.hunters_league.web.vm.CompetitionVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {
    CompetitionDTO toDTO(Competition competition);
    Competition toEntity(CompetitionSaveVM competitionSaveVM);
    @Mapping(target = "currentParticipants", expression = "java(competition.getParticipations().size())")
    CompetitionDetailsDTO toDetailsDTO(Competition competition);

    CompetitionVM toVM(CompetitionDetailsDTO competitionDetailsDTO);


}