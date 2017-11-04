package org.szymie.exercise.boundaries.use_cases.make_reservation;

public class MakeReservationRequest {

    public String username;
    public Long personId;
    public String tableName;

    public MakeReservationRequest(String username, Long personId, String tableName) {
        this.username = username;
        this.personId = personId;
        this.tableName = tableName;
    }
}
