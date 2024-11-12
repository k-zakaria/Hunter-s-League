package com.example.hunters_league.web.vm.mapper;

import com.example.hunters_league.domain.Participation;
import com.example.hunters_league.service.dto.ParticipationDTO;
import com.example.hunters_league.web.vm.ParticipationVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CompetitionMapper.class})
public interface ParticipationMapper {
    ParticipationDTO toDTO(Participation participation);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "competition.id", target = "competitionId")
    ParticipationVM toVM(Participation participation);

    Participation toEntity(ParticipationDTO participationDTO);
}
