package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.*;
import com.example.springjpaweb.service.*;
import com.example.springjpaweb.web.errors.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class CargoController {
    private final CargoService cargoService;
    private final ShipService shipService;

    public CargoController(CargoService cargoService, ShipService shipService) {
        this.cargoService = cargoService;
        this.shipService = shipService;
    }

    @PreAuthorize("hasRole('BOSS')")
    @GetMapping("/cargos/{shipId}")
    public List<Cargo> getCargosOfShip(@PathVariable long shipId){
        return cargoService.getCargosOfShip(shipId);
    }

    @PreAuthorize("hasRole('BOSS')")
    @GetMapping("/cargo/{cargoId}")
    public Optional<Cargo> getCargo(@PathVariable long cargoId){
        return cargoService.findById(cargoId);
    }

    @PreAuthorize("hasRole('BOSS')")
    @PostMapping("/cargo/{shipId}")
    public Cargo createCargo(@PathVariable long shipId, @RequestBody Cargo cargo) {
        Optional<Ship> ship = shipService.findById(shipId);
        cargo.setShip(ship.get());
        return cargoService.save(cargo);
    }

    @PreAuthorize("hasRole('BOSS')")
    @PutMapping("/cargo/{id}")
    public Cargo updateCargo(@PathVariable long id, @RequestBody Cargo cargoData){
        Optional<Cargo> cargoFromDb = cargoService.findById(id);

        if (cargoFromDb.isPresent()) {
            Cargo cargo = cargoFromDb.get();

            cargo.setName(cargoData.getName());
            cargo.setWeight(cargoData.getWeight());
            cargo.setNote(cargoData.getNote());
            cargo.setPrice(cargoData.getPrice());
            cargo.setSender(cargoData.getSender());

            return cargoService.save(cargo);

        } else {
            return new Cargo();
        }
    }

    @PreAuthorize("hasRole('BOSS')")
    @DeleteMapping("/cargo/{id}")
    public void deleteCargo(@PathVariable long id){
        cargoService.delete(id);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAccesDeniedException(AccessDeniedException e) {
        e.printStackTrace();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("P????stup odep??en"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleTransactionSystemException(TransactionSystemException e) {
        e.printStackTrace();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Instanci se nepoda??ilo vytvo??it"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDataIntegrityException(DataIntegrityViolationException e) {
        e.printStackTrace();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Poruseni integrity dat - neunikatni nebo nulova povinna data"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerGeneralException(RuntimeException e) {
        e.printStackTrace();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Nastala chyba"), HttpStatus.BAD_REQUEST);
    }

   @ExceptionHandler
   public ResponseEntity<ErrorResponse> handleException(EmptyResultDataAccessException e) {
       e.printStackTrace();
       return new ResponseEntity<ErrorResponse>(new ErrorResponse("Nenalezen"), HttpStatus.NOT_FOUND);
   }

}
