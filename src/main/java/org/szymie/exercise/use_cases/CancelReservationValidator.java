package org.szymie.exercise.use_cases;

import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.boundaries.Validator;
import org.szymie.exercise.boundaries.repositories.ReservationRepository;
import org.szymie.exercise.boundaries.use_cases.cancel_reservation.CancelReservationRequest;
import org.szymie.exercise.boundaries.use_cases.cancel_reservation.CancelReservationResponse;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class CancelReservationValidator implements Validator<CancelReservationRequest, CancelReservationResponse> {

    private ReservationRepository reservationRepository;

    public CancelReservationValidator(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public CancelReservationResponse validate(CancelReservationRequest request) {

        Optional<Reservation> reservationOptional = reservationRepository.findById(request.id);

        return reservationOptional.map(reservation -> {

            LocalDateTime now = LocalDateTime.now();

            if(!request.username.equals(reservation.getMadeBy().getUsername())) {
                return new CancelReservationResponse(false, true, false, false);
            } else {

                long minutes = Duration.between(now, reservation.getStart()).toMinutes();

                if(reservation.getStart().isBefore(now) || minutes < 5) {
                    return new CancelReservationResponse(false, false, false, true);
                } else {
                    return new CancelReservationResponse(true, false, false, false);
                }
            }
        }).orElse(new CancelReservationResponse(false, false, true, false));
    }
}
