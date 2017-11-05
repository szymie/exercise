package org.szymie.exercise.boundaries.repositories;

import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.application_model.Table;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TableRepository {
    List<Table> findAll(int page, int size);
    Optional<Table> findByName(String name);
    Optional<Table> save(Table person);
    boolean exists(String tableName);
}
