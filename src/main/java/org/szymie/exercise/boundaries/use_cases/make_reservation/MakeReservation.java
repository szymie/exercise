package org.szymie.exercise.boundaries.use_cases.make_reservation;

import org.szymie.exercise.boundaries.Presenter;

public interface MakeReservation {
    void makeReservation(MakeReservationRequest request, Presenter<MakeReservationResponse> presenter);
}
