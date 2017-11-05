package org.szymie.exercise.external.services;

import org.szymie.exercise.boundaries.Presenter;
import java.util.List;

public class ListPresenter<T> implements Presenter<List<T>> {

    public List<T> elements;

    @Override
    public void onResponse(List<T> response) {
        elements = response;
    }
}
