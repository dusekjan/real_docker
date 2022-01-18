package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Ship;
import com.example.springjpaweb.entity.Student;
import com.example.springjpaweb.repository.ShipRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipService {

    private final ShipRepository repository;

    public ShipService(ShipRepository repository){
        this.repository = repository;
    }

    public Optional<Ship> findById(long id) {
        return repository.findById(id);
    }

    public Ship save(Ship ship) {
        return repository.save(ship);
    }

    @PreAuthorize(("hasRole('SUPERADMIN')"))
    public void delete(long id){
        repository.deleteById(id);
    }

    public List<Ship> getAll() {
        return repository.findAll();
    }

}
