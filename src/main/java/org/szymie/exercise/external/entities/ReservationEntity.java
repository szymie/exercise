package org.szymie.exercise.external.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private PersonEntity person;
    @ManyToOne
    private TableEntity table;
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    protected ReservationEntity() {
    }

    public ReservationEntity(PersonEntity person, TableEntity table, Date start, Date end) {
        this(null, person, table, start, end);
    }

    public ReservationEntity(Long id, PersonEntity person, TableEntity table, Date start, Date end) {
        this.id = id;
        this.person = person;
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

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public TableEntity getTable() {
        return table;
    }

    public void setTable(TableEntity table) {
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
