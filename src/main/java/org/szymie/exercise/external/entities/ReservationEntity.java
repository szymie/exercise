package org.szymie.exercise.external.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime start;
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime end;

    protected ReservationEntity() {
    }

    public ReservationEntity(PersonEntity person, TableEntity table, LocalDateTime start, LocalDateTime end) {
        this(null, person, table, start, end);
    }

    public ReservationEntity(Long id, PersonEntity person, TableEntity table, LocalDateTime start, LocalDateTime end) {
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
