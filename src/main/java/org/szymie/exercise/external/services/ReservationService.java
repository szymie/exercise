package org.szymie.exercise.external.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.szymie.exercise.application_model.Reservation;
import org.szymie.exercise.application_model.TableAlreadyReserved;
import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservation;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationRequest;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationResponse;
import org.szymie.exercise.external.dtos.ReservationDto;

import java.util.Collection;

@Service
public class ReservationService {

    private MakeReservation makeReservation;

    @Autowired
    public ReservationService(MakeReservation makeReservation) {
        this.makeReservation = makeReservation;
    }

    public Long addReservation(String loggedAs, ReservationDto reservationDto) {

        MakeReservationPresenter presenter = new MakeReservationPresenter();

        makeReservation.createTable(new MakeReservationRequest(loggedAs, reservationDto.getPersonId(), reservationDto.getTableName(), reservationDto.getStart(), reservationDto.getEnd()),
                presenter);

        return presenter.id;
    }

    private class MakeReservationPresenter implements Presenter<MakeReservationResponse> {

        Long id;

        @Override
        public void onResponse(MakeReservationResponse response) {


            if(response.successful) {
                id = response.reservationId;
            } else if(response.conflictingReservations.isEmpty()) {

            } else {
                throw new TableAlreadyReserved("Table is already reserved", response.conflictingReservations);
            }
        }
    }
}
