package org.szymie.exercise.boundaries.use_cases.make_reservation;

import org.szymie.exercise.application_model.Reservation;

import java.util.Collection;
import java.util.EnumSet;

public class MakeReservationResponse {

    public boolean successful;
    public Long reservationId;
    public boolean notAuthorized;
    public boolean tableNotExists;
    public boolean tooSoon;
    public boolean endBeforeStart;
    public boolean tooLong;
    public Collection<Reservation> conflictingReservations;

    public MakeReservationResponse(boolean successful, Long reservationId, boolean notAuthorized, boolean tableNotExists, boolean tooSoon, boolean endBeforeStart, boolean tooLong, Collection<Reservation> conflictingReservations) {
        this.successful = successful;
        this.reservationId = reservationId;
        this.notAuthorized = notAuthorized;
        this.tableNotExists = tableNotExists;
        this.tooSoon = tooSoon;
        this.endBeforeStart = endBeforeStart;
        this.tooLong = tooLong;
        this.conflictingReservations = conflictingReservations;
    }


    public MakeReservationResponse(EnumSet<MakeReservationErrors> errors, Long reservationId, Collection<Reservation> conflictingReservations) {
        this.successful = errors.isEmpty();
        this.reservationId = reservationId;
        this.notAuthorized = errors.contains(MakeReservationErrors.NOT_AUTHORIZED);
        this.tableNotExists = errors.contains(MakeReservationErrors.TALBE_NOT_EXISTS);
        this.tooSoon = errors.contains(MakeReservationErrors.TOO_SOON);
        this.endBeforeStart = errors.contains(MakeReservationErrors.END_BEFORE_START);
        this.tooLong = errors.contains(MakeReservationErrors.TOO_LONG);
        this.conflictingReservations = conflictingReservations;
    }

    public void setNotAuthorizedTrue() {
        notAuthorized = true;
        setSuccessfulFalse();
    }

    public void setTableNotExistsTrue() {
        tableNotExists = true;
        setSuccessfulFalse();
    }

    public void setTooSoonTrue() {
        tooSoon = true;
        setSuccessfulFalse();
    }

    public void setEndBeforeStartTrue() {
        endBeforeStart = true;
        setSuccessfulFalse();
    }

    public void setTooLongTrue() {
        this.tooLong = true;
        setSuccessfulFalse();
    }

    private void setSuccessfulFalse() {
        successful = false;
    }
}
