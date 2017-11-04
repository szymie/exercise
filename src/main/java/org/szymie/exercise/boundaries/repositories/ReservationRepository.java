package org.szymie.exercise.boundaries.repositories;

import org.szymie.exercise.application_model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public interface ReservationRepository {
    Collection<Reservation> findAllByTableNameAndStartBetweenOrEndBetween(String talbeName, Date start, Date end);
    Reservation save(Reservation reservation);
}
