package com.example.hunters_league.web.rest;

import com.example.hunters_league.domain.Participation;
import com.example.hunters_league.service.ParticipationService;
import com.example.hunters_league.service.dto.CompetitionDetailsDTO;
import com.example.hunters_league.service.dto.ParticipationDTO;
import com.example.hunters_league.web.errors.participation.ParticipationNotFoundException;
import com.example.hunters_league.web.response.ResponseHandler;
import com.example.hunters_league.web.vm.CompetitionVM;
import com.example.hunters_league.web.vm.ParticipationDeleteVM;
import com.example.hunters_league.web.vm.ParticipationVM;
import com.example.hunters_league.web.vm.ScoreUpdateVM;
import com.example.hunters_league.web.vm.mapper.CompetitionMapper;
import com.example.hunters_league.web.vm.mapper.ParticipationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/participation")
@RequiredArgsConstructor
public class ParticipationController {
    private final ParticipationService participationService;
    private final ParticipationMapper participationMapper;
    private final CompetitionMapper competitionMapper;

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody ParticipationDeleteVM participationDeleteVM) {
        try {
            boolean isDeleted = participationService.delete(participationDeleteVM.getId());
            if (isDeleted) {
                return ResponseEntity.ok("Participation deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participation not found");
            }
        } catch (ParticipationNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participation not found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> find(@PathVariable UUID id) {
        Participation participation = participationService.findById(id);
        ParticipationVM participationVM = participationMapper.toVM(participation);
        return ResponseHandler.responseBuilder("Reuquested Participation", HttpStatus.OK, participationVM);
    }

    @PostMapping("/save")
    public ResponseEntity<ParticipationVM> save(@RequestBody ParticipationDTO participationDTO) {
        Participation participation = participationService.save(participationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(participationMapper.toVM(participation));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ParticipationVM> update(@PathVariable UUID id, @RequestBody ParticipationDTO participationDTO) {
        Participation updatedParticipation = participationService.update(id, participationDTO);
        return ResponseEntity.ok(participationMapper.toVM(updatedParticipation));
    }

    @GetMapping("/{participationId}/competition")
    public ResponseEntity<CompetitionVM> getCompetitionDetails(@PathVariable UUID participationId) {
        CompetitionDetailsDTO competitionDetails = participationService.getCompetitionDetailsByParticipant(participationId);
        return ResponseEntity.ok(competitionMapper.toVM(competitionDetails));
    }

    @PutMapping("/updateScore")
    public ResponseEntity<String> updateScore(@RequestBody ScoreUpdateVM scoreUpdateVM) {
        participationService.updateScore(scoreUpdateVM.getParticipationId(), scoreUpdateVM.getScore());
        return ResponseEntity.ok("Score updated successfully");
    }

}
