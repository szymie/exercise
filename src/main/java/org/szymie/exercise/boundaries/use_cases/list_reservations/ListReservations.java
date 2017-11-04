package org.szymie.exercise.boundaries.use_cases.list_reservations;

import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.application_model.Table;
import org.szymie.exercise.boundaries.Presenter;

import java.util.List;

public interface ListReservations {
    void listReservations(int page, int size, Presenter<List<Reservation>> presenter);
    void listReservationsForPerson(Long personId, int page, int size, Presenter<List<Reservation>> presenter);
    void listReservationsForTable(String tableName, int page, int size, Presenter<List<Reservation>> presenter);
}
