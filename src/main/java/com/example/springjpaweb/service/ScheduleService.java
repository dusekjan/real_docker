package com.example.springjpaweb.service;

import com.example.springjpaweb.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }
}
