package org.szymie.exercise.boundaries.use_cases.make_reservation;

import java.util.Date;

public class MakeReservationRequest {

    public String username;
    public Long personId;
    public String tableName;
    public Date start;
    public Date end;

    public MakeReservationRequest(String username, Long personId, String tableName, Date start, Date end) {
        this.username = username;
        this.personId = personId;
        this.tableName = tableName;
        this.start = start;
        this.end = end;
    }
}
