package org.szymie.exercise.boundaries.use_cases.make_reservation;

import org.szymie.exercise.application_model.Reservation;

import java.util.Collection;

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
}
