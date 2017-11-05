package org.szymie.exercise.application_model;

import java.util.Set;

public class Person {

    private Long id;
    private String username;
    private String password;
    private Set<String> roles;

    public Person() {
    }

    public Person(Long id) {
        this(id, null, null, null);
    }

    public Person( String username, String password, Set<String> roles) {
        this(null, username, password, roles);
    }

    public Person(Long id, String username, String password, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
