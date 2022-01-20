package com.example.springjpaweb.repository;

import com.example.springjpaweb.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
