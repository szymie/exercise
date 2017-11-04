package org.szymie.exercise.boundaries.use_cases.make_reservation;

import org.szymie.exercise.application_model.Reservation;

import java.util.Collection;

public class MakeReservationResponse {

    public boolean successful;
    public Long reservationId;
    public Collection<Reservation> conflictingReservations;

    public MakeReservationResponse(boolean successful, Long reservationId, Collection<Reservation> conflictingReservations) {
        this.successful = successful;
        this.reservationId = reservationId;
        this.conflictingReservations = conflictingReservations;
    }
}
