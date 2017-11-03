package org.szymie.exercise.application_model;


import java.util.Date;

public class Reservation {

    private Long id;

    private Person madeBy;
    private Table table;

    private Date start;
    private Date end;

    public Reservation() {
    }

    public Reservation(Long id, Person madeBy, Table table, Date start, Date end) {
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
