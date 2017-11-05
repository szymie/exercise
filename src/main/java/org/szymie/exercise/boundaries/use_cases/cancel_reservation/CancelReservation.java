package org.szymie.exercise.boundaries.use_cases.cancel_reservation;

import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationRequest;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationResponse;

public interface CancelReservation {
    void cancelReservation(CancelReservationRequest request, Presenter<CancelReservationResponse> presenter);
}
