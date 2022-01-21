package com.example.springjpaweb.repository;

import com.example.springjpaweb.entity.Schedule;
import com.example.springjpaweb.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT DISTINCT sch FROM Schedule sch LEFT JOIN sch.ship sh")
    List<Schedule> findSchedulesAndShip();

}
