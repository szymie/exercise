package org.szymie.exercise.boundaries.use_cases.create_person;

import org.szymie.exercise.boundaries.Presenter;

public interface CreatePerson {
    void createPerson(CreatePersonRequest request, Presenter<CreatePersonResponse> presenter);
}
