package com.example.springjpaweb.repository;

import com.example.springjpaweb.entity.Ship;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipRepository extends JpaRepository<Ship, Long> {

}

