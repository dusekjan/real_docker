package com.example.springjpaweb.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Entity
@Table(name="cargo")
public class Cargo {
    @Id
    @GeneratedValue
    private long id;
    private float weight;
    private String name;
    private String note;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="ship_id")
    private Ship ship;

    public Cargo() {

    }

    public Cargo(float weight, String name, String note, BigDecimal price) {
        this.weight = weight;
        this.name = name;
        this.note = note;
        this.price = price;
    }

    public Cargo(float weight, String name, String note, BigDecimal price, Ship ship) {
        this.weight = weight;
        this.name = name;
        this.note = note;
        this.price = price;
        this.ship = ship;
    }

    public long getId() {
        return id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    
}
