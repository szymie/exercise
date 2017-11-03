package org.szymie.exercise.external.entities;

import org.szymie.exercise.application_model.Person;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<PersonEntity> people;

    protected RoleEntity() {
    }

    public RoleEntity(Long id, String name, Set<PersonEntity> people) {
        this.id = id;
        this.name = name;
        this.people = people;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PersonEntity> getPeople() {
        return people;
    }

    public void setPeople(Set<PersonEntity> people) {
        this.people = people;
    }
}
