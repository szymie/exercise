package org.szymie.exercise.external.exceptions;

public class PersonAlreadyExists extends RuntimeException {

    public PersonAlreadyExists() {
    }

    public PersonAlreadyExists(String message) {
        super(message);
    }
}
