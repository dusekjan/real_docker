package com.example.springjpaweb.repository;

import com.example.springjpaweb.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> { }

