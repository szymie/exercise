package org.szymie.exercise.external.services;

import org.szymie.exercise.boundaries.Presenter;
import java.util.List;

public class ListPresenter<T> implements Presenter<List<T>> {

    public List<T> tables;

    @Override
    public void onResponse(List<T> response) {
        tables = response;
    }
}
