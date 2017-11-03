package org.szymie.exercise.use_cases;

import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.boundaries.adapters.PasswordEncoder;
import org.szymie.exercise.boundaries.repositories.PersonRepository;
import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.use_cases.create_person.CreatePerson;
import org.szymie.exercise.boundaries.use_cases.create_person.CreatePersonRequest;
import org.szymie.exercise.boundaries.use_cases.create_person.CreatePersonResponse;

public class CreatePersonImpl implements CreatePerson {

    private PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;

    public CreatePersonImpl(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createPerson(CreatePersonRequest request, Presenter<CreatePersonResponse> presenter) {

        Person newPerson = new Person(null, request.name, passwordEncoder.encode(request.password));
        Person savedPerson = personRepository.save(newPerson);

        presenter.onResponse(new CreatePersonResponse(savedPerson.getId()));
    }
}
