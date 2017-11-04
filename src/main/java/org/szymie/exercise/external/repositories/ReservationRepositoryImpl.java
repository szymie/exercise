package org.szymie.exercise.external.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.application_model.Table;
import org.szymie.exercise.boundaries.repositories.ReservationRepository;
import org.szymie.exercise.external.entities.PersonEntity;
import org.szymie.exercise.external.entities.ReservationEntity;
import org.szymie.exercise.external.entities.TableEntity;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private JpaReservationRepository jpaReservationRepository;
    private JpaPersonRepository jpaPersonRepository;
    private JpaTableRepository jpaTableRepository;

    @Autowired
    public ReservationRepositoryImpl(JpaReservationRepository jpaReservationRepository, JpaPersonRepository jpaPersonRepository, JpaTableRepository jpaTableRepository) {
        this.jpaReservationRepository = jpaReservationRepository;
        this.jpaPersonRepository = jpaPersonRepository;
        this.jpaTableRepository = jpaTableRepository;
    }

    @Override
    public Collection<Reservation> findAllByTableNameAndStartBetweenOrEndBetween(String tableName, Date start, Date end) {

        return jpaReservationRepository.findAllByTableNameAndStartBetweenOrEndBetween(tableName, start, end)
                .stream()
                .map(reservationEntity -> {

                    PersonEntity personEntity = reservationEntity.getPerson();
                    TableEntity tableEntity = reservationEntity.getTable();

                    Person person = new Person(personEntity.getId(), personEntity.getUsername(), personEntity.getPassword());
                    Table table = new Table(tableEntity.getName());

                    return new Reservation(reservationEntity.getId(), person, table, start, end);
                }).collect(Collectors.toList());
    }

    @Override
    public Reservation save(Reservation reservation) {

        PersonEntity personEntityReference = jpaPersonRepository.getOne(reservation.getMadeBy().getId());
        TableEntity tableEntityReference = jpaTableRepository.getOne(reservation.getTable().getName());

        ReservationEntity reservationEntity = new ReservationEntity(personEntityReference, tableEntityReference, reservation.getStart(), reservation.getEnd());
        ReservationEntity savedReservationEntity = jpaReservationRepository.save(reservationEntity);

        return new Reservation(savedReservationEntity.getId(), reservation.getMadeBy(), reservation.getTable(), reservation.getStart(), reservation.getEnd());
    }


}
