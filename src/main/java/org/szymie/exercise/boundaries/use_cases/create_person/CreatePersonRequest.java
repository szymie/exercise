package org.szymie.exercise.boundaries.use_cases.create_person;

public class CreatePersonRequest {

    public String name;
    public String password;

    public CreatePersonRequest() {
    }

    public CreatePersonRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
