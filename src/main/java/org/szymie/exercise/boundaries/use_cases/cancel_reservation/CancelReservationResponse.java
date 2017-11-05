package org.szymie.exercise.boundaries.use_cases.cancel_reservation;

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
}
