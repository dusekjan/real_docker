package com.example.springjpaweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name="cargo")
public class Cargo {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private float weight;

    @Size(min = 1)
    @Column(nullable = false)
    private String name;

    private String note;

    @Column(nullable = false)
    private BigDecimal price;

    @Size(min = 1)
    @Column(nullable = false)
    private String sender;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="ship_id", nullable = false)
    private Ship ship;

    public Cargo() {

    }

    public Cargo(float weight, String name, String note, BigDecimal price, String sender) {
        this.weight = weight;
        this.name = name;
        this.note = note;
        this.price = price;
        this.sender = sender;
    }

    public Cargo(float weight, String name, String note, BigDecimal price, String sender,Ship ship) {
        this.weight = weight;
        this.name = name;
        this.note = note;
        this.price = price;
        this.sender = sender;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
