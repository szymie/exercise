package org.szymie.exercise.boundaries;

public interface Validator<RequestT, ResponseT> {
    ResponseT validate(RequestT request);
}
