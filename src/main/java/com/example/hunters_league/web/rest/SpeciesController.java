package com.example.hunters_league.web.rest;

import com.example.hunters_league.domain.Species;
import com.example.hunters_league.service.SpeciesService;
import com.example.hunters_league.service.dto.SpeciesDTO;
import com.example.hunters_league.web.errors.species.SpeciesNotFoundException;
import com.example.hunters_league.web.vm.SpeciesDeleteVM;
import com.example.hunters_league.web.vm.SpeciesVM;
import com.example.hunters_league.web.vm.mapper.SpeciesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody SpeciesDeleteVM speciesDeleteVM) {
        try {
            boolean isDeleted = speciesService.delete(speciesDeleteVM.getId());
            if (isDeleted) {
                return ResponseEntity.ok("Species deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Species not found");
            }
        } catch (SpeciesNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Species not found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SpeciesVM> find(@PathVariable UUID id) {
        Species species = speciesService.findById(id);
        return ResponseEntity.ok(speciesMapper.toVM(species));
    }

    @PostMapping("/save")
    public ResponseEntity<SpeciesVM> save(@RequestBody SpeciesDTO speciesDTO) {
        Species savedSpecies = speciesService.saveSpecies(speciesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(speciesMapper.toVM(savedSpecies));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SpeciesVM> updateSpecies(@PathVariable UUID id, @RequestBody SpeciesDTO speciesDTO) {
        Species species = speciesService.updateSpecies(id, speciesDTO);
        return ResponseEntity.ok(speciesMapper.toVM(species));
    }

    @GetMapping("allSpecies")
    public ResponseEntity<Page<SpeciesVM>> getAllSpecies(
            @RequestParam(defaultValue = "0" ) int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<Species> speciesPage = speciesService.getAllSpecies(pageable);
        Page<SpeciesVM> speciesVMPage = speciesPage.map(speciesMapper::toVM);
        return ResponseEntity.ok(speciesVMPage);
    }
}
