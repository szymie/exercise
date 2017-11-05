package org.szymie.exercise.external.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.boundaries.repositories.PersonRepository;
import org.szymie.exercise.external.entities.PersonEntity;
import org.szymie.exercise.external.entities.RoleEntity;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
    public List<Person> findAll(int page, int size) {
        return jpaPersonRepository.findAll(new PageRequest(page, size))
                .map(this::entityToModel)
                .getContent();
    }

    @Override
    public Collection<Person> findAll() {
        return jpaPersonRepository.findAll().stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    private Person entityToModel(PersonEntity personEntity) {
        return new Person(personEntity.getId(), personEntity.getUsername(), personEntity.getPassword(),
                personEntity.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet()));
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return jpaPersonRepository.findByUsername(username)
                .map(this::entityToModel);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(jpaPersonRepository.findOne(id))
                .map(this::entityToModel);
    }

    @Override
    public Optional<Person> save(Person person) {

        try {
            RoleEntity customerRole = jpaRoleRepository.findByName("CUSTOMER");
            PersonEntity savedPersonEntity = jpaPersonRepository.save(new PersonEntity(person.getUsername(), person.getPassword(), Collections.singleton(customerRole)));
            return Optional.of(entityToModel(savedPersonEntity));
        } catch (DataIntegrityViolationException dke) {
            return Optional.empty();
        }
    }

    @Override
    public boolean exists(Long id) {

        try {
            Long referenceId = jpaPersonRepository.getOne(id).getId();
            return true;
        } catch (EntityNotFoundException ignore) {
            return false;
        }
    }
}
