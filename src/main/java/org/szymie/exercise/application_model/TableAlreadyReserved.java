package org.szymie.exercise.application_model;

import java.util.Collection;

public class TableAlreadyReserved extends RuntimeException {

    public Collection<Reservation> conflictingReservations;

    public TableAlreadyReserved(String message, Collection<Reservation> conflictingReservations) {
        super(message);
        this.conflictingReservations = conflictingReservations;
    }
}
