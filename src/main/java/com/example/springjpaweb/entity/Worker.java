package com.example.springjpaweb.entity;

import com.example.springjpaweb.enums.Role;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    // TODO password neukladat v plain textu!!!
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private String firstName;
    private String sureName;
    private String phone;

    // reflexive oneToMany

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "boss_id")
    private Worker boss;

    @JsonManagedReference
    @OneToMany(mappedBy = "boss")
    private List<Worker> bosses;

    public Worker() {
    }

    public Worker(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Role.WORKER;
    }

    public Worker(String email, String password, String firstName, String sureName, String phone, Worker boss) {
        this.email = email;
        this.password = password;
        this.role = Role.WORKER;
        this.firstName = firstName;
        this.sureName = sureName;
        this.phone = phone;
        this.boss = boss;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Worker getBoss() {
        return boss;
    }

    public void setBoss(Worker boss) {
        this.boss = boss;
    }

    @JsonIgnore
    public List<Worker> getBosses() {
        return bosses;
    }

    public void setBosses(List<Worker> bosses) {
        this.bosses = bosses;
    }
}
