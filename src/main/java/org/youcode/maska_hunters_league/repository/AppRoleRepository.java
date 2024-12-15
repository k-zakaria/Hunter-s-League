package org.youcode.maska_hunters_league.repository;

import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.maska_hunters_league.domain.entities.AppRole;

import java.util.UUID;

public interface AppRoleRepository extends JpaRepository<AppRole, UUID> {
    AppRole getRoleByName(String name);
}
