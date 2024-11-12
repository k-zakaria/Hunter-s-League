package com.example.hunters_league.service.impl;

import com.example.hunters_league.domain.Hunt;
import com.example.hunters_league.repository.HuntRepository;
import com.example.hunters_league.service.HuntService;
import com.example.hunters_league.service.dto.HuntDTO;
import com.example.hunters_league.web.errors.hunt.HuntNotFoundException;
import com.example.hunters_league.web.vm.mapper.HuntMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HuntServiceImpl implements HuntService {

    private final HuntRepository huntRepository;
    private final HuntMapper huntMapper;

    @Override
    public Hunt findById(UUID id) {
        return huntRepository.findById(id)
                .orElseThrow(() -> new HuntNotFoundException("Hunt not found"));
    }

    @Override
    public Hunt save(HuntDTO huntDTO) {
        Hunt hunt = huntMapper.toEntity(huntDTO);
        return huntRepository.save(hunt);
    }

    @Override
    public Hunt update(UUID id, HuntDTO huntDTO) {
        Hunt existingHunt = huntRepository.findById(id)
                .orElseThrow(() -> new HuntNotFoundException("Hunt not found"));

        existingHunt.setWeight(huntDTO.getWeight());
        existingHunt.setSpecies(existingHunt.getSpecies());
        existingHunt.setParticipation(existingHunt.getParticipation());

        return huntRepository.save(existingHunt);
    }

    @Override
    public boolean delete(UUID id) {
        Hunt hunt = huntRepository.findById(id)
                .orElseThrow(() -> new HuntNotFoundException("Hunt not found"));
        huntRepository.delete(hunt);
        return true;
    }
}
