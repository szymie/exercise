package org.szymie.exercise.use_cases;

import org.szymie.exercise.application_model.Table;
import org.szymie.exercise.boundaries.repositories.TableRepository;
import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.use_cases.create_table.CreateTable;
import org.szymie.exercise.boundaries.use_cases.create_table.CreateTableRequest;
import org.szymie.exercise.boundaries.use_cases.create_table.CreateTableResponse;

import java.util.Optional;

public class CreateTableImpl implements CreateTable {

    private TableRepository tableRepository;

    public CreateTableImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public void createTable(CreateTableRequest request, Presenter<CreateTableResponse> presenter) {

        Table newPerson = new Table(request.name);
        Optional<Table> savedPerson = tableRepository.save(newPerson);

        presenter.onResponse(savedPerson.map(ignore -> new CreateTableResponse(false))
                .orElse(new CreateTableResponse(true)));
    }
}