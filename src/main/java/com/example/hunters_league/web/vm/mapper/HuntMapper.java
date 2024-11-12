package com.example.hunters_league.web.vm.mapper;

import com.example.hunters_league.domain.Hunt;
import com.example.hunters_league.service.dto.HuntDTO;
import com.example.hunters_league.web.vm.HuntVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SpeciesMapper.class, ParticipationMapper.class})
public interface HuntMapper {
    HuntDTO toDTO(Hunt hunt);

    @Mapping(source = "species.id", target = "speciesId")
    @Mapping(source = "participation.id", target = "participationId")
    HuntVM toVM(Hunt hunt);

    Hunt toEntity(HuntDTO huntDTO);
}
