package org.szymie.exercise.external.exceptions;

import org.szymie.exercise.external.dtos.TableReservationErrorDto;

public class CancelTableReservationException extends RuntimeException {

    public TableReservationErrorDto tableReservationErrorDto;

    public CancelTableReservationException(String message, TableReservationErrorDto tableReservationErrorDto) {
        super(message);
        this.tableReservationErrorDto = tableReservationErrorDto;
    }
}
