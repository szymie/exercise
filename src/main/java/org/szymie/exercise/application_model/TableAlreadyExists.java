package org.szymie.exercise.application_model;

public class TableAlreadyExists extends RuntimeException {

    public TableAlreadyExists() {
    }

    public TableAlreadyExists(String message) {
        super(message);
    }
}
