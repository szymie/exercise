package org.szymie.exercise.external.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.szymie.exercise.application_model.Table;
import org.szymie.exercise.external.dtos.TableDto;
import org.szymie.exercise.external.exceptions.TableAlreadyExists;
import org.szymie.exercise.boundaries.use_cases.create_table.CreateTable;
import org.szymie.exercise.boundaries.use_cases.create_table.CreateTableRequest;
import org.szymie.exercise.boundaries.use_cases.list_tables.ListTables;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<TableDto> getTables(int page, int size) {

        ListPresenter<Table> presenter = new ListPresenter<>();
        listTables.listTables(page, size, presenter);

        return presenter.elements.stream()
                .map(table -> new TableDto(table.getName()))
                .collect(Collectors.toList());
    }
}
