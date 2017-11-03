package org.szymie.exercise.boundaries;

public interface Presenter<T> {
    void onResponse(T response);
}
