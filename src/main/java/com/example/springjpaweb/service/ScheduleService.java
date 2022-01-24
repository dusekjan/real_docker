package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Schedule;
import com.example.springjpaweb.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void delete(long id){
        repository.deleteById(id);
    }

    public Optional<Schedule> findById(long id) {
        return repository.findById(id);
    }

    public List<Schedule> getAll() {
        return repository.findAll();
    }
}
