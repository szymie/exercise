package org.szymie.exercise.use_cases;

import org.szymie.exercise.application_model.Table;
import org.szymie.exercise.boundaries.repositories.TableRepository;
import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.use_cases.list_tables.ListTables;

import java.util.Collection;
import java.util.List;

public class ListTablesImpl implements ListTables {

    private TableRepository tableRepository;

    public ListTablesImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public void listTables(int page, int size, Presenter<List<Table>> presenter) {
        presenter.onResponse(tableRepository.findAll(page, size));
    }
}
