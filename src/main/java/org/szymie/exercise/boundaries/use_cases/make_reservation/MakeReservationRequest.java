package org.szymie.exercise.boundaries.use_cases.make_reservation;

import java.time.LocalDateTime;

public class MakeReservationRequest {

    public String username;
    public Long personId;
    public String tableName;
    public LocalDateTime start;
    public LocalDateTime end;

    public MakeReservationRequest(String username, Long personId, String tableName, LocalDateTime start, LocalDateTime end) {
        this.username = username;
        this.personId = personId;
        this.tableName = tableName;
        this.start = start;
        this.end = end;
    }
}
