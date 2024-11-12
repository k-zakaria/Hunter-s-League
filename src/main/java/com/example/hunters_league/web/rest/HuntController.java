package com.example.hunters_league.web.rest;

import com.example.hunters_league.domain.Hunt;
import com.example.hunters_league.service.HuntService;
import com.example.hunters_league.service.dto.HuntDTO;
import com.example.hunters_league.web.vm.HuntVM;
import com.example.hunters_league.web.vm.mapper.HuntMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/hunt")
@RequiredArgsConstructor
public class HuntController {

    private final HuntService huntService;
    private final HuntMapper huntMapper;

    @GetMapping("/find/{id}")
    public ResponseEntity<HuntVM> find(@PathVariable UUID id) {
        Hunt hunt = huntService.findById(id);
        return ResponseEntity.ok(huntMapper.toVM(hunt));
    }

    @PostMapping("/save")
    public ResponseEntity<HuntVM> save(@RequestBody HuntDTO huntDTO) {
        Hunt hunt = huntService.save(huntDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(huntMapper.toVM(hunt));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HuntVM> update(@PathVariable UUID id, @RequestBody HuntDTO huntDTO) {
        Hunt updatedHunt = huntService.update(id, huntDTO);
        return ResponseEntity.ok(huntMapper.toVM(updatedHunt));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        huntService.delete(id);
        return ResponseEntity.ok("Hunt deleted");
    }
}
