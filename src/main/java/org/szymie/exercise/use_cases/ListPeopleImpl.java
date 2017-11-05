package org.szymie.exercise.use_cases;

import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.repositories.PersonRepository;
import org.szymie.exercise.boundaries.use_cases.list_people.ListPeople;

import java.util.List;

public class ListPeopleImpl implements ListPeople {

    private PersonRepository personRepository;

    public ListPeopleImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void listPeople(int page, int size, Presenter<List<Person>> presenter) {
        presenter.onResponse(personRepository.findAll(page, size));
    }
}
