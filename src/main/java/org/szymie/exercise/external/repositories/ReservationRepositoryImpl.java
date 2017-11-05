package org.szymie.exercise.external.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import org.szymie.exercise.application_model.Person;
import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.application_model.Table;
import org.szymie.exercise.boundaries.repositories.ReservationRepository;
import org.szymie.exercise.external.entities.PersonEntity;
import org.szymie.exercise.external.entities.ReservationEntity;
import org.szymie.exercise.external.entities.RoleEntity;
import org.szymie.exercise.external.entities.TableEntity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
    public Collection<Reservation> findAllByTableNameAndStartBetweenOrEndBetween(String tableName, LocalDateTime start, LocalDateTime end) {

        return jpaReservationRepository.findAllByTableNameAndStartBetweenOrEndBetween(tableName, start, end).stream()
                .map(this::entityToModel).collect(Collectors.toList());
    }

    private Reservation entityToModel(ReservationEntity reservationEntity) {

        PersonEntity personEntity = reservationEntity.getPerson();
        TableEntity tableEntity = reservationEntity.getTable();

        Person person = new Person(personEntity.getId(), personEntity.getUsername(), personEntity.getPassword(), personEntity.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet()));
        Table table = new Table(tableEntity.getName());

        return new Reservation(reservationEntity.getId(), person, table, reservationEntity.getStart(), reservationEntity.getEnd());
    }

    @Override
    public Reservation save(Reservation reservation) {

        PersonEntity personEntityReference = jpaPersonRepository.getOne(reservation.getMadeBy().getId());
        TableEntity tableEntityReference = jpaTableRepository.getOne(reservation.getTable().getName());

        ReservationEntity reservationEntity = new ReservationEntity(personEntityReference, tableEntityReference, reservation.getStart(), reservation.getEnd());
        ReservationEntity savedReservationEntity = jpaReservationRepository.save(reservationEntity);

        return new Reservation(savedReservationEntity.getId(), reservation.getMadeBy(), reservation.getTable(), reservation.getStart(), reservation.getEnd());
    }

    @Override
    public List<Reservation> findAll(int page, int size) {
        return jpaReservationRepository.findAll(new PageRequest(page, size))
                .map(this::entityToModel)
                .getContent();
    }

    @Override
    public List<Reservation> findAllByPersonId(Long personId, int page, int size) {
        return jpaReservationRepository.findAllByPersonId(personId, new PageRequest(page, size))
                .map(this::entityToModel)
                .getContent();
    }

    @Override
    public List<Reservation> findAllByTableName(String tableName, int page, int size) {
        return jpaReservationRepository.findAllByTableName(tableName, new PageRequest(page, size))
                .map(this::entityToModel)
                .getContent();
    }

    @Override
    public boolean delete(Long id) {
        return jpaReservationRepository.deleteById(id) > 0;
    }

    @Override
    public boolean exists(Long id) {
        return jpaReservationRepository.exists(id);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return jpaReservationRepository.findById(id).map(this::entityToModel);
    }
}
