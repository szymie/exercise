package org.szymie.exercise.external.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TableDto {

    @NotNull
    @Size(min = 1)
    private String name;

    protected TableDto() {
    }

    public TableDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
