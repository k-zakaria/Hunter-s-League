package com.example.hunters_league.web.rest;

import com.example.hunters_league.domain.Competition;
import com.example.hunters_league.service.CompetitionService;
import com.example.hunters_league.service.dto.CompetitionDTO;
import com.example.hunters_league.web.vm.CompetitionSaveVM;
import com.example.hunters_league.web.vm.mapper.CompetitionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/competition")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;
    private final CompetitionMapper competitionMapper;


    @GetMapping("/find/{id}")
    public ResponseEntity<CompetitionDTO> find(@PathVariable String id) {
        Competition competition = competitionService.findById(id);
        CompetitionDTO competitionDTO = competitionMapper.toDTO(competition);
        return ResponseEntity.ok(competitionDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<CompetitionDTO> save(@RequestBody CompetitionSaveVM competitionSaveVM) {
        Competition competition = competitionMapper.toEntity(competitionSaveVM);
        Competition savedCompetition = competitionService.save(competition);
        CompetitionDTO competitionDTO = competitionMapper.toDTO(savedCompetition);
        return new ResponseEntity<>(competitionDTO, HttpStatus.CREATED);
    }
}