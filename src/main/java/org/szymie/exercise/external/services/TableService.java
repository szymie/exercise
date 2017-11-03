package org.szymie.exercise.external.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.szymie.exercise.application_model.Table;
import org.szymie.exercise.application_model.TableAlreadyExists;
import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.use_cases.create_table.CreateTable;
import org.szymie.exercise.boundaries.use_cases.create_table.CreateTableRequest;
import org.szymie.exercise.boundaries.use_cases.list_tables.ListTables;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class TableService {

    private CreateTable createTable;
    private ListTables listTables;

    @Autowired
    public TableService(CreateTable createTable, ListTables listTables) {
        this.createTable = createTable;
        this.listTables = listTables;
    }

    public void addTable(String name) {

        createTable.createTable(new CreateTableRequest(name), response -> {
            if(response.alreadyExists) {
                throw new TableAlreadyExists("Table with such name already exists");
            }
        });
    }

    public List<Table> getTables(int page, int size) {

        ListTablesPresenter presenter = new ListTablesPresenter();
        listTables.listTables(page, size, presenter);

        return presenter.tables;
    }

    private class ListTablesPresenter implements Presenter<List<Table>> {

        List<Table> tables;

        @Override
        public void onResponse(List<Table> response) {
            tables = response;
        }
    }

}
