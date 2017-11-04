package org.szymie.exercise.external.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.szymie.exercise.application_model.PersonAlreadyExists;
import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.application_model.TableAlreadyExists;
import org.szymie.exercise.application_model.TableAlreadyReserved;
import org.szymie.exercise.external.dtos.ErrorInfo;
import org.szymie.exercise.external.dtos.ReservationDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({PersonAlreadyExists.class})
    public @ResponseBody ErrorInfo handleUserAlreadyExists(HttpServletRequest request, Exception exception) {
        return new ErrorInfo<>(request.getRequestURL().toString(), exception, null);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({TableAlreadyExists.class})
    public @ResponseBody ErrorInfo handleTableAlreadyExists(HttpServletRequest request, Exception exception) {
        return new ErrorInfo<>(request.getRequestURL().toString(), exception, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({TableAlreadyReserved.class})
    public @ResponseBody ErrorInfo handleTableAlreadyReserved(HttpServletRequest request, TableAlreadyReserved exception) {
        return new ErrorInfo<>(request.getRequestURL().toString(), exception,
                new ConflictingReservations(exception.conflictingReservations.stream()
                        .map(reservation -> new ReservationDto(reservation.getId(), reservation.getMadeBy().getId(),
                                reservation.getTable().getName(), reservation.getStart(), reservation.getEnd())).collect(Collectors.toList()) ));
    }

    public class ConflictingReservations {

        public Collection<ReservationDto> conflictingReservations;

        public ConflictingReservations() {
        }

        public ConflictingReservations(Collection<ReservationDto> conflictingReservations) {
            this.conflictingReservations = conflictingReservations;
        }
    }

}
