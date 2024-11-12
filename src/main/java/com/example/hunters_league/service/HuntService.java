package com.example.hunters_league.service;

import com.example.hunters_league.domain.Hunt;
import com.example.hunters_league.service.dto.HuntDTO;

import java.util.UUID;

public interface HuntService {
    Hunt findById(UUID id);

    Hunt save(HuntDTO huntDTO);

    Hunt update(UUID id, HuntDTO huntDTO);

    boolean delete(UUID id);
}
