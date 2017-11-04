package org.szymie.exercise.external.exceptions;

public class TableAlreadyExists extends RuntimeException {

    public TableAlreadyExists() {
    }

    public TableAlreadyExists(String message) {
        super(message);
    }
}
