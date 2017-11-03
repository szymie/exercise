package org.szymie.exercise.external.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class PersonDto {

    @Null
    private Long id;
    @NotNull
    @Size(min = 1)
    private String username;
    @NotNull
    @Size(min = 1)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    protected PersonDto() {
    }

    public PersonDto(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
