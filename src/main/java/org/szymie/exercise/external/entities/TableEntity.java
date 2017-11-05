package org.szymie.exercise.external.entities;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "elements")
public class TableEntity implements Persistable<String> {

    @Id
    private String name;
    @OneToMany(mappedBy = "table")
    private List<ReservationEntity> reservations;
    @Transient
    private boolean isNew;

    protected TableEntity() {
        isNew = false;
    }

    public TableEntity(String name, List<ReservationEntity> reservations, boolean isNew) {
        this.name = name;
        this.reservations = reservations;
        this.isNew = isNew;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Override
    public String getId() {
        return name;
    }
}

