package org.szymie.exercise.external.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.boundaries.use_cases.list_people.ListPeople;
import org.szymie.exercise.external.exceptions.PersonAlreadyExists;
import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.use_cases.create_person.CreatePerson;
import org.szymie.exercise.boundaries.use_cases.create_person.CreatePersonRequest;
import org.szymie.exercise.boundaries.use_cases.create_person.CreatePersonResponse;
import org.szymie.exercise.external.dtos.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private CreatePerson createPerson;
    private ListPeople listPeople;

    @Autowired
    public PersonService(CreatePerson createPerson, ListPeople listPeople) {
        this.createPerson = createPerson;
        this.listPeople = listPeople;
    }

    public Long addPerson(PersonDto personDto) {

        CreatePersonPresenter presenter = new CreatePersonPresenter();
        createPerson.createPerson(new CreatePersonRequest(personDto.getUsername(), personDto.getPassword()), presenter);

        return presenter.id;
    }

    public List<PersonDto> getPeople(int page, int size) {

        ListPresenter<Person> presenter = new ListPresenter<>();

        listPeople.listPeople(page, size, presenter);

        return presenter.elements.stream()
                .map(person -> new PersonDto(person.getId(), person.getUsername(), person.getPassword()))
                .collect(Collectors.toList());
    }

    private class CreatePersonPresenter implements Presenter<CreatePersonResponse> {

        private Long id;

        @Override
        public void onResponse(CreatePersonResponse response) {

            if(response.alreadyExists) {
                throw new PersonAlreadyExists("Person with such username already exists");
            }

            id = response.id;
        }
    }
}
