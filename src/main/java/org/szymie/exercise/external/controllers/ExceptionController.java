package org.szymie.exercise.external.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.szymie.exercise.external.exceptions.PersonAlreadyExists;
import org.szymie.exercise.external.exceptions.TableAlreadyExists;
import org.szymie.exercise.external.exceptions.TableAlreadyReserved;
import org.szymie.exercise.external.dtos.ErrorInfo;
import org.szymie.exercise.external.dtos.ReservationDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({PersonAlreadyExists.class, TableAlreadyExists.class})
    public @ResponseBody ErrorInfo handleUserAlreadyExists(HttpServletRequest request, Exception exception) {
        return new ErrorInfo<>(request.getRequestURL().toString(), exception, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({TableAlreadyReserved.class})
    public @ResponseBody ErrorInfo handleTableAlreadyReserved(HttpServletRequest request, TableAlreadyReserved exception) {
        return new ErrorInfo<>(request.getRequestURL().toString(), exception, exception.tableReservationErrorDto);
    }
}
