package org.szymie.exercise.use_cases;

import org.szymie.exercise.boundaries.Presenter;
import org.szymie.exercise.boundaries.TransactionExecutor;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservation;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationReponse;
import org.szymie.exercise.boundaries.use_cases.make_reservation.MakeReservationRequest;

public class MakeReservationImpl implements MakeReservation {

    private TransactionExecutor transactionExecutor;

    public MakeReservationImpl(TransactionExecutor transactionExecutor) {
        this.transactionExecutor = transactionExecutor;
    }

    @Override
    public void createTable(MakeReservationRequest request, Presenter<MakeReservationReponse> presenter) {



    }
}
