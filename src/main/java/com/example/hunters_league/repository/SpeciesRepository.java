package com.example.hunters_league.repository;

import com.example.hunters_league.domain.Species;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {
    Page<Species> findAll(Pageable pageable);
}
