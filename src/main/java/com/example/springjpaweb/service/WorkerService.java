package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.repository.WorkerRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    private final WorkerRepository repository;

    public WorkerService(WorkerRepository repository) {
        this.repository = repository;
    }

    public Worker save(Worker worker){
        return repository.save(worker);
    }

    public List<Worker> getAll() {
        return repository.findAll();
    }
}
