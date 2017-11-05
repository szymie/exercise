package org.szymie.exercise.use_cases;

import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.application_model.Table;
import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.Validator;
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
    private Validator<MakeReservationRequest, MakeReservationResponse> validator;

    public MakeReservationImpl(TransactionExecutor transactionExecutor, ReservationRepository reservationRepository,
                               Validator<MakeReservationRequest, MakeReservationResponse> validator) {
        this.transactionExecutor = transactionExecutor;
        this.reservationRepository = reservationRepository;
        this.validator = validator;
    }

    @Override
    public void makeReservation(MakeReservationRequest request, Presenter<MakeReservationResponse> presenter) {


        MakeReservationResponse response = validator.validate(request);

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
}
