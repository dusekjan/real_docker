package com.example.springjpaweb.entity;

import com.example.springjpaweb.enums.CargoType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name="ship")
public class Ship {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String company;

    @Enumerated(EnumType.STRING)
    private CargoType cargoType; // podrobneji v entite Cargo

    //lod neni vlastnikem ani jednoho vztahu takze nepotrebuje mit gettery a settery
    @JsonManagedReference
    @OneToMany(mappedBy = "ship")
    private List<Cargo> cargo;

    @OneToOne(mappedBy = "ship")
    private Schedule schedule;

    public Ship() {

    }

    public Ship(String name, String company) {
        this.name = name;
        this.company = company;
    }

    public Ship(String name, String company, CargoType cargoType) {
        this.name = name;
        this.company = company;
        this.cargoType = cargoType;
    }

    public long getId() {
        return id;
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

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public List<Cargo> getCargo() {
        return cargo;
    }

    public void setCargo(List<Cargo> cargo) {
        this.cargo = cargo;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", cargoType=" + cargoType +
                '}';
    }
}
