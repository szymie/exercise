package org.szymie.exercise.application_model;


import java.time.LocalDateTime;
import java.util.Date;

public class Reservation {

    private Long id;

    private Person madeBy;
    private Table table;

    private LocalDateTime start;
    private LocalDateTime end;

    public Reservation() {
    }

    public Reservation(Person madeBy, Table table, LocalDateTime start, LocalDateTime end) {
        this(null, madeBy, table, start, end);
    }

    public Reservation(Long id, Person madeBy, Table table, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.madeBy = madeBy;
        this.table = table;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(Person madeBy) {
        this.madeBy = madeBy;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
