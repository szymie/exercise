package org.szymie.exercise.external.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;

public class ReservationDto {

    @Null
    private Long id;
    @NotNull
    private Long personId;
    @NotNull
    @Size(min = 1)
    private String tableName;
    @Future
    private Date start;
    @Future
    private Date end;

    protected ReservationDto() {
    }

    public ReservationDto(Long id, Long personId, String tableName, Date start, Date end) {
        this.id = id;
        this.personId = personId;
        this.tableName = tableName;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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
