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

import java.util.Collection;
import java.util.Collections;

public class MakeReservationImpl implements MakeReservation {

    private TransactionExecutor transactionExecutor;
    private ReservationRepository reservationRepository;
    private TableRepository tableRepository;
    private PersonRepository personRepository;

    public MakeReservationImpl(TransactionExecutor transactionExecutor, ReservationRepository reservationRepository) {
        this.transactionExecutor = transactionExecutor;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void createTable(MakeReservationRequest request, Presenter<MakeReservationResponse> presenter) {

        transactionExecutor.execute(() -> {

            Collection<Reservation> conflictingReservations = reservationRepository.findAllByTableNameAndStartBetweenOrEndBetween(request.tableName, request.start, request.end);

            MakeReservationResponse response;

            if(conflictingReservations.isEmpty()) {

                Reservation savedReservation = reservationRepository.save(
                        new Reservation(null, new Person(request.personId, null, null), new Table(request.tableName), request.start, request.end));

                response = new MakeReservationResponse(true, savedReservation.getId(), Collections.emptyList());
            } else {
                response = new MakeReservationResponse(false, null, conflictingReservations);
            }

            presenter.onResponse(response);

            return response.successful;
        });
    }
}
