package org.szymie.exercise.external.entities;

import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tables")
public class TableEntity implements Persistable<String> {

    @Id
    private String name;
    @Transient
    private boolean isNew;

    protected TableEntity() {
        isNew = false;
    }

    public TableEntity(String name,boolean isNew) {
        this.name = name;
        this.isNew = isNew;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

