package org.szymie.exercise.external.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.szymie.exercise.application_model.TableAlreadyExists;
import org.szymie.exercise.external.dtos.ErrorInfo;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(TableAlreadyExists.class)
    public @ResponseBody ErrorInfo handleUserAlreadyExists(HttpServletRequest request, Exception exception) {
        return new ErrorInfo(request.getRequestURL().toString(), exception);
    }

}
