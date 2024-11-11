package com.example.hunters_league.web.rest;

import com.example.hunters_league.domain.Species;
import com.example.hunters_league.service.SpeciesService;
import com.example.hunters_league.service.dto.SpeciesDTO;
import com.example.hunters_league.web.vm.mapper.SpeciesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/species")
@RequiredArgsConstructor
public class SpeciesController {

    private final SpeciesService speciesService;
    private final SpeciesMapper speciesMapper;

//    @PostMapping("/delete")
//    public ResponseEntity<String> delete(@RequestBody SpeciesDeleteVM speciesDeleteVM) {
//        try {
//            boolean isDeleted = speciesService.delete(speciesDeleteVM.getId());
//            if (isDeleted) {
//                return ResponseEntity.ok("Species deleted");
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Species not found");
//            }
//        } catch (SpeciesNotFoundException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Species not found");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//        }
//    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SpeciesDTO> find(@PathVariable UUID id) {
        Species species = speciesService.findById(id);
        SpeciesDTO speciesDTO = speciesMapper.toDTO(species);
        return ResponseEntity.ok(speciesDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<SpeciesDTO> save(@RequestBody SpeciesDTO speciesDTO) {
        Species savedSpecies = speciesService.saveSpecies(speciesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(speciesMapper.toDTO(savedSpecies));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SpeciesDTO> updateSpecies(@PathVariable UUID id, @RequestBody SpeciesDTO speciesDTO) {
        Species updatedSpecies = speciesService.updateSpecies(id, speciesDTO);
        return ResponseEntity.ok(speciesMapper.toDTO(updatedSpecies));
    }
}
