package org.szymie.exercise.use_cases;

import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.boundaries.Validator;
import org.szymie.exercise.boundaries.repositories.PersonRepository;
import org.szymie.exercise.boundaries.repositories.ReservationRepository;
import org.szymie.exercise.boundaries.use_cases.cancel_reservation.CancelReservationRequest;
import org.szymie.exercise.boundaries.use_cases.cancel_reservation.CancelReservationErrors;
import org.szymie.exercise.boundaries.use_cases.cancel_reservation.CancelReservationResponse;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Optional;

public class CancelReservationValidator implements Validator<CancelReservationRequest, CancelReservationResponse> {

    private ReservationRepository reservationRepository;
    private PersonRepository personRepository;

    public CancelReservationValidator(ReservationRepository reservationRepository, PersonRepository personRepository) {
        this.reservationRepository = reservationRepository;
        this.personRepository = personRepository;
    }

    @Override
    public CancelReservationResponse validate(CancelReservationRequest request) {

        Optional<Reservation> reservationOptional = reservationRepository.findById(request.id);

        return reservationOptional.map(reservation -> {

            LocalDateTime now = LocalDateTime.now();

            return personRepository.findByUsername(request.username).map(requestingPerson -> {

                if (requestingPerson.getUsername().equals(reservation.getMadeBy().getUsername()) || requestingPerson.getRoles().contains("OWNER")) {

                    long minutes = Duration.between(now, reservation.getStart()).toMinutes();

                    if (reservation.getStart().isBefore(now) || minutes < 5) {
                        return new CancelReservationResponse(EnumSet.of(CancelReservationErrors.TOO_LATE));
                    } else {
                        return new CancelReservationResponse(EnumSet.noneOf(CancelReservationErrors.class));
                    }
                } else {
                    return null;
                }
            }).orElse(new CancelReservationResponse(EnumSet.of(CancelReservationErrors.NOT_AUTHORIZED)));

        }).orElse(new CancelReservationResponse(EnumSet.of(CancelReservationErrors.NOT_EXISTS)));
    }
}
