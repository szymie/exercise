package org.szymie.exercise.external.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.szymie.exercise.application_model.Table;
import org.szymie.exercise.boundaries.repositories.TableRepository;
import org.szymie.exercise.external.entities.TableEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TableRepositoryImpl implements TableRepository {

    private JpaTableRepository jpaTableRepository;

    @Autowired
    public TableRepositoryImpl(JpaTableRepository jpaTableRepository) {
        this.jpaTableRepository = jpaTableRepository;
    }


    @Override
    public List<Table> findAll(int page, int size) {



        return jpaTableRepository.findAll(new PageRequest(page, size))
                .map(personEntity -> new Table(personEntity.getName()))
                .getContent();
    }

    @Override
    public Optional<Table> findByName(String name) {
        return Optional.ofNullable(jpaTableRepository.findOne(name))
                .map(tableEntity -> new Table(tableEntity.getName()));
    }

    @Override
    public Optional<Table> save(Table table) {

        try {
            TableEntity savedTableEntity = jpaTableRepository.save(new TableEntity(table.getName(), Collections.emptyList(), true));
            return Optional.of(new Table(savedTableEntity.getName()));
        } catch (DataIntegrityViolationException dke) {
            return Optional.empty();
        }
    }
}
