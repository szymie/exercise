package org.szymie.exercise.external.exceptions;

import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.external.dtos.ReservationDto;
import org.szymie.exercise.external.dtos.TableReservationErrorDto;

import java.util.Collection;

public class TableAlreadyReserved extends RuntimeException {

    public TableReservationErrorDto tableReservationErrorDto;

    public TableAlreadyReserved(String message, TableReservationErrorDto tableReservationErrorDto) {
        super(message);
        this.tableReservationErrorDto = tableReservationErrorDto;
    }
}
