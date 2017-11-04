package org.szymie.exercise.external.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TableReservationErrorDto {

    private Collection<ReservationDto> conflictingReservations;
    private Collection<String> errors;

    protected TableReservationErrorDto() {
    }

    public TableReservationErrorDto(Collection<ReservationDto> conflictingReservations, Collection<String> errors) {
        this.conflictingReservations = conflictingReservations;
        this.errors = errors;
    }

    public Collection<ReservationDto> getConflictingReservations() {
        return conflictingReservations;
    }

    public void setConflictingReservations(Collection<ReservationDto> conflictingReservations) {
        this.conflictingReservations = conflictingReservations;
    }

    public Collection<String> getErrors() {
        return errors;
    }

    public void setErrors(Collection<String> errors) {
        this.errors = errors;
    }
}