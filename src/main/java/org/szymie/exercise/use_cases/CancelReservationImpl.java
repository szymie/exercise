package org.szymie.exercise.use_cases;

import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.Validator;
import org.szymie.exercise.boundaries.repositories.ReservationRepository;
import org.szymie.exercise.boundaries.use_cases.cancel_reservation.CancelReservation;
import org.szymie.exercise.boundaries.use_cases.cancel_reservation.CancelReservationRequest;
import org.szymie.exercise.boundaries.use_cases.cancel_reservation.CancelReservationResponse;

public class CancelReservationImpl implements CancelReservation {

    private ReservationRepository reservationRepository;
    private Validator<CancelReservationRequest, CancelReservationResponse> validator;

    public CancelReservationImpl(ReservationRepository reservationRepository, Validator<CancelReservationRequest, CancelReservationResponse> validator) {
        this.reservationRepository = reservationRepository;
        this.validator = validator;
    }

    @Override
    public void cancelReservation(CancelReservationRequest request, Presenter<CancelReservationResponse> presenter) {

        CancelReservationResponse response = validator.validate(request);

        if(response.successful) {
            boolean existed = reservationRepository.delete(request.id);
            response.notExists = !existed;
        }

        presenter.onResponse(response);
    }
}
