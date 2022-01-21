package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Cargo;
import com.example.springjpaweb.entity.Schedule;
import com.example.springjpaweb.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }
    public Schedule save(Schedule schedule) {
        return repository.save(schedule);
    }

    public List<Schedule> getSchedulesAndShips(){
        return repository.findSchedulesAndShip();
    }

    public List<Schedule> getAll() {
        return repository.findAll();
    }
}
