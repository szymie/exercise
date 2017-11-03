package org.szymie.exercise.boundaries.use_cases.create_table;

public class CreateTableResponse {

    public boolean alreadyExists;

    public CreateTableResponse() {
    }

    public CreateTableResponse(boolean alreadyExists) {
        this.alreadyExists = alreadyExists;
    }
}
