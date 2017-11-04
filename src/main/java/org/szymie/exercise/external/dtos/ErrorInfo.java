package org.szymie.exercise.external.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorInfo<T> {

    public String url;
    public String exceptionMessage;
    public T details;

    public ErrorInfo(String url, Exception exceptionMessage, T details) {
        this.url = url;
        this.exceptionMessage = exceptionMessage.getLocalizedMessage();
        this.details = details;
    }
}
