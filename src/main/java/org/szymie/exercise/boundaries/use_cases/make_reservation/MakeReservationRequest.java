package org.szymie.exercise.boundaries.use_cases.make_reservation;

public class MakeReservationRequest {

    public Long personId;
    public String tableName;

    public MakeReservationRequest(Long personId, String tableName) {
        this.personId = personId;
        this.tableName = tableName;
    }
}
