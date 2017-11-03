package org.szymie.exercise.application_model;

public class PersonAlreadyExists extends RuntimeException {

    public PersonAlreadyExists() {
    }

    public PersonAlreadyExists(String message) {
        super(message);
    }
}
