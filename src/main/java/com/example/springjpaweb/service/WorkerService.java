package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.repository.WorkerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    private final WorkerRepository repository;

    public WorkerService(WorkerRepository repository) {
       this.repository = repository;
    }

//    private final PasswordEncoder passwordEncoder;
//    public WorkerService(WorkerRepository repository, PasswordEncoder passwordEncoder) {
//       this.repository = repository;
//       this.passwordEncoder = passwordEncoder;
//    }

    /* Do databaze kvuli ladeni UserDetailService ukladam heslo v plain textu */
    public Worker save(Worker worker){
        // encrypt password
        // worker.setPassword(passwordEncoder.encode(worker.getPassword()));

        return repository.save(worker);
    }


    public Worker findByEmail(String email){
        return repository.findWorkerByEmail(email);
    }


    public Worker update(Worker worker){
        return repository.save(worker);
    }

    public Optional<Worker> findById(long id){
        Optional<Worker> worker = repository.findById(id);
        return worker;
    }

    public void delete(long id){
        repository.deleteById(id);
    }

    public List<Worker> getAll() {
        List<Worker> workers = repository.findAll();
        return workers;
    }

}
