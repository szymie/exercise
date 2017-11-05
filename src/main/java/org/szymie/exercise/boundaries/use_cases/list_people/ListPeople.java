package org.szymie.exercise.boundaries.use_cases.list_people;

import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.boundaries.Presenter;

import java.util.List;

public interface ListPeople {
    void listPeople(int page, int size, Presenter<List<Person>> presenter);

}
