package org.szymie.exercise.use_cases;

import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.application_model.Table;
import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.adapters.TransactionExecutor;
import org.szymie.exercise.boundaries.repositories.PersonRepository;
import org.szymie.exercise.boundaries.repositories.ReservationRepository;
import org.szymie.exercise.boundaries.repositories.TableRepository;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservation;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationResponse;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationRequest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class MakeReservationImpl implements MakeReservation {

    private TransactionExecutor transactionExecutor;
    private ReservationRepository reservationRepository;
    private TableRepository tableRepository;
    private PersonRepository personRepository;

    public MakeReservationImpl(TransactionExecutor transactionExecutor, ReservationRepository reservationRepository, PersonRepository personRepository) {
        this.transactionExecutor = transactionExecutor;
        this.reservationRepository = reservationRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void makeReservation(MakeReservationRequest request, Presenter<MakeReservationResponse> presenter) {

        MakeReservationResponse response = new MakeReservationResponse(true, null, false, false, false, false, Collections.emptyList());

        validatePersonId(request, response);
        validateStartAndEnd(request, response);

        if(response.successful) {

            transactionExecutor.execute(() -> {

                Collection<Reservation> conflictingReservations = reservationRepository.findAllByTableNameAndStartBetweenOrEndBetween(request.tableName, request.start, request.end);

                if(conflictingReservations.isEmpty()) {

                    Reservation savedReservation = reservationRepository.save(
                            new Reservation(null, new Person(request.personId, null, null), new Table(request.tableName), request.start, request.end));
                    response.reservationId = savedReservation.getId();
                } else {
                    response.successful = false;
                    response.conflictingReservations = conflictingReservations;
                }

                return response.successful;
            });
        }

        presenter.onResponse(response);
    }

    private void validatePersonId(MakeReservationRequest request, MakeReservationResponse response) {

        Person person = personRepository.findById(request.personId)
                .orElse(new Person(null, "", null));

        if(!person.getUsername().equals(request.username)) {
            response.notAuthorized = true;
            response.successful = false;
        }
    }

    private void validateStartAndEnd(MakeReservationRequest request, MakeReservationResponse response) {

        LocalDateTime now = LocalDateTime.now();

        if(request.start.isBefore(now) || Duration.between(now, request.start).toMinutes() < 30) {
            response.tooSoon = true;
            response.successful = false;
        }

        if(request.end.isBefore(request.start)) {
            response.endBeforeStart = true;
            response.successful = false;
        }

        long minutes = Duration.between(request.start, request.end).toMinutes();

        if(minutes > 60) {
            response.tooLong = true;
            response.successful = false;
        }
    }
}
