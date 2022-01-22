package com.example.springjpaweb.repository;

import com.example.springjpaweb.entity.Cargo;
import com.example.springjpaweb.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

    @Query("SELECT c FROM Cargo c LEFT JOIN c.ship s WHERE s.id = :id")
    List<Cargo> findCargosByShipId(
            @Param("id") Long id
    );
}
