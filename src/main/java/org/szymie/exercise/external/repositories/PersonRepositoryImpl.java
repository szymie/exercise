package org.szymie.exercise.external.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.boundaries.repositories.PersonRepository;
import org.szymie.exercise.external.entities.PersonEntity;
import org.szymie.exercise.external.entities.RoleEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private JpaPersonRepository jpaPersonRepository;
    private JpaRoleRepository jpaRoleRepository;

    @Autowired
    public PersonRepositoryImpl(JpaPersonRepository jpaPersonRepository, JpaRoleRepository jpaRoleRepository) {
        this.jpaPersonRepository = jpaPersonRepository;
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Collection<Person> findAll() {
        return jpaPersonRepository.findAll().stream()
                .map(personEntity -> new Person(personEntity.getId(), personEntity.getUsername(), personEntity.getPassword()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return jpaPersonRepository.findByUsername(username)
                .map(personEntity -> new Person(personEntity.getId(), personEntity.getUsername(), personEntity.getPassword()));
    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(jpaPersonRepository.findOne(id))
                .map(personEntity -> new Person(personEntity.getId(), personEntity.getUsername(), personEntity.getPassword()));
    }

    @Override
    public Optional<Person> save(Person person) {

        try {
            RoleEntity customerRole = jpaRoleRepository.findByName("CUSTOMER");
            PersonEntity savedPersonEntity = jpaPersonRepository.save(new PersonEntity(person.getUsername(), person.getPassword(), Collections.singleton(customerRole)));
            return Optional.of(new Person(savedPersonEntity.getId(), savedPersonEntity.getUsername(), savedPersonEntity.getPassword()));
        } catch (DataIntegrityViolationException dke) {
            return Optional.empty();
        }
    }
}
