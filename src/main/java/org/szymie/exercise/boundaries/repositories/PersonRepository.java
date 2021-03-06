package org.szymie.exercise.boundaries.repositories;

import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.application_model.Table;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<Person> findAll(int page, int size);
    Collection<Person> findAll();
    Optional<Person> findByUsername(String username);
    Optional<Person> findById(Long id);
    Optional<Person> save(Person person);
    boolean exists(Long id);
}
