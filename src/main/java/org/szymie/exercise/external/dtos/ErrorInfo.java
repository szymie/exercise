package org.szymie.exercise.external.dtos;

public class ErrorInfo {

    public String url;
    public String exception;

    public ErrorInfo(String url, Exception exception) {
        this.url = url;
        this.exception = exception.getLocalizedMessage();
    }
}
