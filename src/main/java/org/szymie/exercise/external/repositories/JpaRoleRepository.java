package org.szymie.exercise.external.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.szymie.exercise.external.entities.RoleEntity;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
