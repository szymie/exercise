package org.szymie.exercise.boundaries.repositories;

import org.szymie.exercise.application_model.Reservation;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Collection<Reservation> findAllByTableNameAndStartBetweenOrEndBetween(String tableName, LocalDateTime start, LocalDateTime end);
    Reservation save(Reservation reservation);
    List<Reservation> findAll(int page, int size);
    List<Reservation> findAllByPersonId(Long personId, int page, int size);
    List<Reservation> findAllByTableName(String tableName, int page, int size);
    boolean delete(Long id);
    boolean exists(Long id);
    Optional<Reservation> findById(Long id);
}
