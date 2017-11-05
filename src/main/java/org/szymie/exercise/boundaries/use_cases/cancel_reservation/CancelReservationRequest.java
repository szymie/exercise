package org.szymie.exercise.boundaries.use_cases.cancel_reservation;

public class CancelReservationRequest {

    public String username;
    public Long id;

    public CancelReservationRequest(String username, Long id) {
        this.username = username;
        this.id = id;
    }
}
