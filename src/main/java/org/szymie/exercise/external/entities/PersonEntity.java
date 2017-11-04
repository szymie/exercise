package org.szymie.exercise.external.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "people")
public class PersonEntity  {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;
    @OneToMany(mappedBy = "person")
    private List<ReservationEntity> reservations;

    protected PersonEntity() {
    }

    public PersonEntity(Long id) {
        this(id, null, null, new HashSet<>(), new ArrayList<>());
    }

    public PersonEntity(String username, String password) {
        this(username, password, new HashSet<>());
    }

    public PersonEntity(String username, String password, Set<RoleEntity> roles) {
        this(null, username, password, roles);
    }

    public PersonEntity(Long id, String username, String password, Set<RoleEntity> roles) {
        this(id, username, password, roles, new ArrayList<>());
    }

    public PersonEntity(Long id, String username, String password, Set<RoleEntity> roles, List<ReservationEntity> reservations) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.reservations = reservations;
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

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }
}

