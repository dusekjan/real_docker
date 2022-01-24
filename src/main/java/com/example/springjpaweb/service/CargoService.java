package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Cargo;
import com.example.springjpaweb.entity.Ship;
import com.example.springjpaweb.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    private final CargoRepository repository;

    public CargoService(CargoRepository repository){
        this.repository = repository;
    }

    public void delete(long id){
        repository.deleteById(id);
    }

    public Cargo save(Cargo cargo) {
        return repository.save(cargo);
    }

    public List<Cargo> getAll() {
        return repository.findAll();
    }

    public Optional<Cargo> findById(long id) {
        return repository.findById(id);
    }

    public List<Cargo> getCargosOfShip(long shipId) {
        return repository.findCargosByShipId(shipId);
    }
}
