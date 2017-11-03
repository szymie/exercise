package org.szymie.exercise.external.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.szymie.exercise.application_model.PersonAlreadyExists;
import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.use_cases.create_person.CreatePerson;
import org.szymie.exercise.boundaries.use_cases.create_person.CreatePersonRequest;
import org.szymie.exercise.boundaries.use_cases.create_person.CreatePersonResponse;
import org.szymie.exercise.external.dtos.PersonDto;

@Service
public class PersonService {

    private CreatePerson createPerson;

    @Autowired
    public PersonService(CreatePerson createPerson) {
        this.createPerson = createPerson;
    }

    public Long addPerson(PersonDto personDto) {

        CreatePersonPresenter presenter = new CreatePersonPresenter();
        createPerson.createPerson(new CreatePersonRequest(personDto.getUsername(), personDto.getPassword()), presenter);

        return presenter.id;
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
