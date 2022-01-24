package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.Cargo;
import com.example.springjpaweb.entity.Schedule;
import com.example.springjpaweb.entity.Ship;
import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.enums.CargoType;
import com.example.springjpaweb.enums.StateOfShip;
import com.example.springjpaweb.service.CargoService;
import com.example.springjpaweb.service.ScheduleService;
import com.example.springjpaweb.service.ShipService;
import com.example.springjpaweb.service.WorkerService;
import com.example.springjpaweb.web.errors.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Transactional
public class ScheduleShipController {
    private final ShipService shipService;
    private final ScheduleService scheduleService;
    private final CargoService cargoService;

    public ScheduleShipController(ShipService shipService, ScheduleService scheduleService, CargoService cargoService) {
        this.shipService = shipService;
        this.scheduleService = scheduleService;
        this.cargoService = cargoService;
    }

    @GetMapping("/schedule/{id}")
    public Optional<Schedule> getSchedule(@PathVariable long id){
        return scheduleService.findById(id);
    }

    @PostMapping("/scheduleAndShip")
    public void createStudent(@RequestBody Map<String, String> data) {
        CargoType cargoType = CargoType.valueOf(data.get("cargoType"));
        Ship ship = new Ship(data.get("name"), data.get("company"), cargoType);

        StateOfShip state = StateOfShip.valueOf(data.get("state"));
        Schedule schedule = new Schedule(data.get("arrivalTime"),
                data.get("departureTime"),
                state,
                ship);


        System.out.println(shipService.save(ship));
        System.out.println(scheduleService.save(schedule));
    }

    @PutMapping("/ship/{id}")
    public Ship updateShip(@PathVariable long id, @RequestBody Ship shipData){
        Optional<Ship> shipFromDb = shipService.findById(id);

        if (shipFromDb.isPresent()) {
            Ship ship = shipFromDb.get();

            ship.setName(shipData.getName());
            ship.setCompany(shipData.getCompany());
            ship.setCargoType(shipData.getCargoType());

            return shipService.save(ship);

        } else {
            return new Ship();
        }
    }

    @PutMapping("/schedule/{id}")
    public Schedule updateSchedule(@PathVariable long id, @RequestBody Schedule scheduleData){
        Optional<Schedule> scheduleFromDb = scheduleService.findById(id);

        if (scheduleFromDb.isPresent()) {
            Schedule schedule = scheduleFromDb.get();

            schedule.setArrivalTime(scheduleData.getArrivalTime());
            schedule.setDepartureTime(scheduleData.getDepartureTime());
            schedule.setState(scheduleData.getState());

            return scheduleService.save(schedule);

        } else {
            return new Schedule();
        }
    }


    @Transactional
    @DeleteMapping("/scheduleAndShipWithCargos/{scheduleId}")
    public void deleteStudent(@PathVariable long scheduleId){

        // find crucial data for start deletions
        long shipId = scheduleService.findById(scheduleId).get().getShip().getId();
        List<Cargo> cargos = cargoService.getCargosOfShip(shipId);

        // deleting
        for (Cargo cargo : cargos){
            cargoService.delete(cargo.getId());
        }
        scheduleService.delete(scheduleId);
        shipService.delete(shipId);
    }




    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleTransactionSystemException(TransactionSystemException e) {
        e.printStackTrace();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Instanci se nepodařilo uložit"), HttpStatus.INTERNAL_SERVER_ERROR);
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
