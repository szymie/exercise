package org.szymie.exercise.external.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.szymie.exercise.external.entities.ReservationEntity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface JpaReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query("select r from ReservationEntity r where r.table.name = ?1 and (r.start between ?2 and ?3 or r.end between ?2 and ?3)")
    Collection<ReservationEntity> findAllByTableNameAndStartBetweenOrEndBetween(String tableName, LocalDateTime start, LocalDateTime end);

    Page<ReservationEntity> findAllByPersonId(Long personId, Pageable pageable);
    Page<ReservationEntity> findAllByTableName(String tableName, Pageable pageable);
}
