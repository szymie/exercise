package org.szymie.exercise.use_cases;

import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.repositories.ReservationRepository;
import org.szymie.exercise.boundaries.use_cases.list_reservations.ListReservations;

import java.util.List;

public class ListReservationImpl implements ListReservations {

    private ReservationRepository reservationRepository;

    public ListReservationImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void listReservations(int page, int size, Presenter<List<Reservation>> presenter) {
        presenter.onResponse(reservationRepository.findAll(page, size));
    }

    @Override
    public void listReservationsForPerson(Long personId, int page, int size, Presenter<List<Reservation>> presenter) {
        presenter.onResponse(reservationRepository.findAllByPersonId(personId, page, size));
    }

    @Override
    public void listReservationsForTable(String tableName, int page, int size, Presenter<List<Reservation>> presenter) {
        presenter.onResponse(reservationRepository.findAllByTableName(tableName, page, size));
    }
}
