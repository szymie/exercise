package org.szymie.exercise.boundaries.use_cases.cancel_reservation;

import java.util.EnumSet;

public class CancelReservationResponse {

    public boolean successful;
    public boolean notAuthorized;
    public boolean notExists;
    public boolean tooLate;

    public CancelReservationResponse(boolean successful, boolean notAuthorized, boolean notExists, boolean tooLate) {
        this.successful = successful;
        this.notAuthorized = notAuthorized;
        this.notExists = notExists;
        this.tooLate = tooLate;
    }


    public CancelReservationResponse(EnumSet<CancelReservationErrors> errors) {
        this.successful = errors.isEmpty();
        this.notAuthorized = errors.contains(CancelReservationErrors.NOT_AUTHORIZED);
        this.notExists = errors.contains(CancelReservationErrors.NOT_EXISTS);
        this.tooLate = errors.contains(CancelReservationErrors.TOO_LATE);
    }
}
