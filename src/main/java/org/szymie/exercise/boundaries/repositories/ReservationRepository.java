package org.szymie.exercise.boundaries.repositories;

import org.szymie.exercise.application_model.Reservation;

import java.util.Collection;
import java.util.Date;

public interface ReservationRepository {
    Collection<Reservation> findAllBetweenDates(Date start, Date end);
}
