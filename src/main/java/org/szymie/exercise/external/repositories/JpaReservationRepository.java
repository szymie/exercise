package org.szymie.exercise.external.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.szymie.exercise.external.entities.ReservationEntity;

import java.util.Collection;
import java.util.Date;

public interface JpaReservationRepository extends PagingAndSortingRepository<ReservationEntity, Long> {


    Collection<ReservationEntity> findAllByStartBetweenOrEndBetween(Date start, Date end);


}
