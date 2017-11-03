package org.szymie.exercise.boundaries.use_cases.create_person;

public class CreatePersonResponse  {

    public Long id;
    public boolean alreadyExists;

    public CreatePersonResponse() {
    }

    public CreatePersonResponse(Long id, boolean alreadyExists) {
        this.id = id;
        this.alreadyExists = alreadyExists;
    }
}
