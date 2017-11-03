package org.szymie.exercise.external.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.szymie.exercise.external.entities.PersonEntity;

import java.util.Optional;

public interface JpaPersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByUsername(String name);
}
