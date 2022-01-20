package com.example.springjpaweb.entity;

import com.example.springjpaweb.enums.StateOfShip;

import javax.persistence.*;

@Entity
@Table(name="schedule")
public class Schedule {
    @Id
    @GeneratedValue
    private long id;
    private String arrivalTime;
    private String departureTime;

    @Enumerated(EnumType.STRING)
    private StateOfShip state;

    @OneToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;

    public Schedule() {
    }

    public Schedule(String arrivalTime, String departureTime, StateOfShip state, Ship ship) {
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.state = state;
        this.ship = ship;
    }

    public long getId() {
        return id;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public StateOfShip getState() {
        return state;
    }

    public void setState(StateOfShip state) {
        this.state = state;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }


}
