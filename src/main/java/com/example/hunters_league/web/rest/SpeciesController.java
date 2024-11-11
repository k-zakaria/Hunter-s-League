package com.example.hunters_league.web.rest;

import com.example.hunters_league.repository.SpeciesRepository;
import com.example.hunters_league.service.SpeciesService;
import com.example.hunters_league.service.dto.SpeciesDTO;
import com.example.hunters_league.web.vm.SpeciesVM;
import com.example.hunters_league.web.vm.mapper.SpeciesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/species")
@RequiredArgsConstructor
public class SpeciesController {

    private final SpeciesService speciesService;
    private final SpeciesMapper speciesMapper;
    private final SpeciesRepository speciesRepository;

    @PostMapping("/save")
    public ResponseEntity<SpeciesDTO> save(@RequestBody SpeciesVM speciesVM){
        SpeciesDTO speciesDTO = speciesMapper.toDTO(speciesMapper.toEntity(speciesVM));
        return ResponseEntity.ok(speciesService.save(speciesDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SpeciesDTO> update(@PathVariable UUID id,@RequestBody SpeciesVM speciesVM){
        SpeciesDTO speciesDTO = speciesMapper.toDTO(speciesMapper.toEntity(speciesVM));
        return ResponseEntity.ok(speciesService.update(id, speciesDTO));
    }

    @GetMapping("find/{id}")
    public ResponseEntity<SpeciesDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(speciesService.findById(id));
    }

    @DeleteMapping("deleted/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        speciesService.deleted(id);
        return ResponseEntity.noContent().build();
    }


}
