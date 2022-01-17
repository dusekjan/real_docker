package com.example.springjpaweb.entity;

import javax.persistence.*;

@Entity
public class Ship {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String company;

    @OneToOne
    private Student student;

    public Ship() {
    }

    public Ship(String name, String company) {
        this.name = name;
        this.company = company;
    }

    public Ship(String name, String company, Student student) {
        this.name = name;
        this.company = company;
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
