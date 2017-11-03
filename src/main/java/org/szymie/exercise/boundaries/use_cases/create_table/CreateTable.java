package org.szymie.exercise.boundaries.use_cases.create_table;

import org.szymie.exercise.boundaries.Presenter;

public interface CreateTable {
    void createTable(CreateTableRequest request, Presenter<CreateTableResponse> presenter);
}
