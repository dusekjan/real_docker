package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Cargo;
import com.example.springjpaweb.entity.Ship;
import com.example.springjpaweb.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    private final CargoRepository repository;

    public CargoService(CargoRepository repository){
        this.repository = repository;
    }

    public Cargo save(Cargo cargo) {
        return repository.save(cargo);
    }

    public List<Cargo> getAll() {
        return repository.findAll();
    }

    public List<Cargo> getCargosOfShip(Long id) {
        return repository.findCargosByShipId(id);
    }
}
