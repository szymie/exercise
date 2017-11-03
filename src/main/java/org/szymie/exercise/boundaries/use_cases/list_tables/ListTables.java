package org.szymie.exercise.boundaries.use_cases.list_tables;

import org.szymie.exercise.application_model.Table;
import org.szymie.exercise.boundaries.Presenter;

import java.util.Collection;
import java.util.List;

public interface ListTables {
    void listTables(int page, int size, Presenter<List<Table>> presenter);
}
