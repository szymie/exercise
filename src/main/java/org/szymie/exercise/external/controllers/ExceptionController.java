package org.szymie.exercise.external.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.szymie.exercise.external.exceptions.CancelTableReservationException;
import org.szymie.exercise.external.exceptions.PersonAlreadyExists;
import org.szymie.exercise.external.exceptions.TableAlreadyExists;
import org.szymie.exercise.external.exceptions.MakeTableReservationException;
import org.szymie.exercise.external.dtos.ErrorInfo;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({PersonAlreadyExists.class, TableAlreadyExists.class})
    public @ResponseBody ErrorInfo handleAlreadyExists(HttpServletRequest request, Exception exception) {
        return new ErrorInfo<>(request.getRequestURL().toString(), exception, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MakeTableReservationException.class})
    public @ResponseBody ErrorInfo handleMakeTableReservationException(HttpServletRequest request, MakeTableReservationException exception) {
        return new ErrorInfo<>(request.getRequestURL().toString(), exception, exception.tableReservationErrorDto);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({CancelTableReservationException.class})
    public @ResponseBody ErrorInfo handleCancelTableReservationException(HttpServletRequest request, CancelTableReservationException exception) {
        return new ErrorInfo<>(request.getRequestURL().toString(), exception, exception.tableReservationErrorDto);
    }
}
