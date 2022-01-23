package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    private final WorkerRepository repository;

    public WorkerService(WorkerRepository repository) {
        this.repository = repository;
    }

    public Worker save(Worker worker){
        return repository.save(worker);
    }

    public Optional<Worker> findById(long id){
        Optional<Worker> worker = repository.findById(id);
        return worker;
    }

    public List<Worker> getAll() {
        List<Worker> workers = repository.findAll();
        return workers;
    }
}
