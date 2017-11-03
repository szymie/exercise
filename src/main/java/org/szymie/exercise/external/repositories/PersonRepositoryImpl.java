package org.szymie.exercise.external.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.boundaries.repositories.PersonRepository;
import org.szymie.exercise.external.entities.PersonEntity;
import org.szymie.exercise.external.entities.RoleEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
                .map(personEntity -> new Person(personEntity.getId(), personEntity.getName(), personEntity.getPassword()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> findByName(String name) {
        return jpaPersonRepository.findByName(name)
                .map(personEntity -> new Person(personEntity.getId(), personEntity.getName(), personEntity.getPassword()));
    }

    @Override
    public Person save(Person person) {
        RoleEntity customerRole = jpaRoleRepository.findByName("CUSTOMER");
        PersonEntity savedPersonEntity = jpaPersonRepository.save(new PersonEntity(null, person.getName(), person.getPassword(), Collections.singleton(customerRole)));
        return new Person(savedPersonEntity.getId(), savedPersonEntity.getName(), savedPersonEntity.getPassword());
    }
}
