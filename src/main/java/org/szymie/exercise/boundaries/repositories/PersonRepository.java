package org.szymie.exercise.boundaries.repositories;

import org.szymie.exercise.application_model.Person;

import java.util.Collection;
import java.util.Optional;

public interface PersonRepository {
    Collection<Person> findAll();
    Optional<Person> findByName(String name);
    Person save(Person person);
}
