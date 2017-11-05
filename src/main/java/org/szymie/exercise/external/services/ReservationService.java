package org.szymie.exercise.external.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.boundaries.use_cases.cancel_reservation.CancelReservation;
import org.szymie.exercise.boundaries.use_cases.cancel_reservation.CancelReservationRequest;
import org.szymie.exercise.boundaries.use_cases.cancel_reservation.CancelReservationResponse;
import org.szymie.exercise.boundaries.use_cases.list_reservations.ListReservations;
import org.szymie.exercise.external.dtos.TableReservationErrorDto;
import org.szymie.exercise.external.exceptions.CancelTableReservationException;
import org.szymie.exercise.external.exceptions.MakeTableReservationException;
import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservation;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationRequest;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationResponse;
import org.szymie.exercise.external.dtos.ReservationDto;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private MakeReservation makeReservation;
    private ListReservations listReservations;
    private CancelReservation cancelReservation;

    @Autowired
    public ReservationService(MakeReservation makeReservation, ListReservations listReservations, CancelReservation cancelReservation) {
        this.makeReservation = makeReservation;
        this.listReservations = listReservations;
        this.cancelReservation = cancelReservation;
    }

    public Long addReservation(String loggedAs, ReservationDto reservationDto) {

        MakeReservationPresenter presenter = new MakeReservationPresenter();

        makeReservation.makeReservation(new MakeReservationRequest(loggedAs, reservationDto.getPersonId(), reservationDto.getTableName(), reservationDto.getStart(), reservationDto.getEnd()),
                presenter);

        return presenter.id;
    }

    private class MakeReservationPresenter implements Presenter<MakeReservationResponse> {

        private Long id;

        @Override
        public void onResponse(MakeReservationResponse response) {

            if(response.successful) {
                id = response.reservationId;
            } else {

                List<String> errors = extractErrors(response);

                List<ReservationDto> conflictingReservations = response.conflictingReservations.stream()
                        .map(reservation -> new ReservationDto(reservation.getId(), reservation.getMadeBy().getId(),
                                reservation.getTable().getName(), reservation.getStart(), reservation.getEnd())).collect(Collectors.toList());

                TableReservationErrorDto tableReservationErrorDto = new TableReservationErrorDto(conflictingReservations, errors);

                throw new MakeTableReservationException("Reservation could not be made", tableReservationErrorDto);
            }
        }

        private List<String> extractErrors(MakeReservationResponse response) {

            List<String> errors = new LinkedList<>();

            if(response.notAuthorized) {
                errors.add("You cannot make reservation for other person");
                return errors;
            }

            if(response.tableNotExists) {
                errors.add("Chosen table does not exist");
                return errors;
            }

            if(response.tooSoon) {
                errors.add("Reservation must start at least 30 minutes after current time");
            }

            if(response.endBeforeStart) {
                errors.add("Reservation's end time must be later than start time");
            }

            if(response.tooLong) {
                errors.add("Reservation cannot last longer than 60 minutes");
            }

            if(!response.conflictingReservations.isEmpty()) {
                errors.add("There are conflicting reservations at chosen time");
            }

            return errors;
        }
    }

    public void deleteReservation(String loggedAs, Long reservationId) {

        CancelReservationPresenter presenter = new CancelReservationPresenter();

        cancelReservation.cancelReservation(new CancelReservationRequest(loggedAs, reservationId), presenter);
    }

    private class CancelReservationPresenter implements Presenter<CancelReservationResponse> {

        @Override
        public void onResponse(CancelReservationResponse response) {

            if(!response.successful) {

                List<String> errors = extractErrors(response);

                TableReservationErrorDto tableReservationErrorDto = new TableReservationErrorDto(null, errors);
                throw new CancelTableReservationException("It is not possible to cancel this reservation", tableReservationErrorDto);
            }
        }

        private List<String> extractErrors(CancelReservationResponse response) {

            List<String> errors = new LinkedList<>();

            if(response.notAuthorized) {
                errors.add("You cannot make reservation for other person");
            }

            if(response.notExists) {
                errors.add("Reservation does not exist");
            }

            if(response.tooLate) {
                errors.add("It is too late to cancel this reservation");
            }

            return errors;
        }
    }


    public List<ReservationDto> getReservations(int page, int size) {

        ListPresenter<Reservation> presenter = new ListPresenter<>();

        listReservations.listReservations(page, size, presenter);

        return presenter.tables.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    private ReservationDto modelToDto(Reservation reservation) {
        return new ReservationDto(reservation.getId(), reservation.getMadeBy().getId(),
                reservation.getTable().getName(), reservation.getStart(), reservation.getEnd());
    }

    public List<ReservationDto> getReservationsByPersonId(Long personId, int page, int size) {

        ListPresenter<Reservation> presenter = new ListPresenter<>();

        listReservations.listReservationsForPerson(personId, page, size, presenter);

        return presenter.tables.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public List<ReservationDto> getReservationsByTableName(String tableName, int page, int size) {

        ListPresenter<Reservation> presenter = new ListPresenter<>();

        listReservations.listReservationsForTable(tableName, page, size, presenter);

        return presenter.tables.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }
}