package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.*;
import com.example.springjpaweb.enums.CargoType;
import com.example.springjpaweb.enums.Role;
import com.example.springjpaweb.enums.StateOfShip;
import com.example.springjpaweb.service.*;
import com.example.springjpaweb.web.errors.ErrorResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final ShipService shipService;
    private final CargoService cargoService;
    private final ScheduleService scheduleService;
    private final WorkerService workerService;

    public StudentController(StudentService studentService, ShipService shipService, CargoService cargoService, ScheduleService scheduleService, WorkerService workerService) {
        this.studentService = studentService;
        this.shipService = shipService;
        this.cargoService = cargoService;
        this.scheduleService = scheduleService;
        this.workerService = workerService;

        initData();

    }

    @GetMapping("/cargos/{id}")
    public List<Cargo> getCargosOfShip(@PathVariable long id){
        return cargoService.getCargosOfShip(id);
    }

    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable long id, @RequestBody Student student){
       student.setId(id);
       return studentService.save(student);
   }

   @GetMapping("/students")
   public List<Student> getAllStudents(){
        return studentService.getAll();
   }

   @DeleteMapping("/student/{id}")
   public void deleteStudent(@PathVariable long id){
       studentService.delete(id);
   }


   @GetMapping("/student/{id}")
   public Optional<Student> getStudentById(@PathVariable long id){
       return studentService.findById(id);
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



   private void initData(){
       Ship ship1 = shipService.save(new Ship("Lod1", "Linda", CargoType.ANIMALS));
       Ship ship2 = shipService.save(new Ship("Lod2", "Linda", CargoType.ANIMALS));
       Ship ship3 = shipService.save(new Ship("Lod3", "Karla", CargoType.CHEMICAL));
       Ship ship4 = shipService.save(new Ship("Lod4", "Karla", CargoType.DRYBULK));
       Ship ship5 = shipService.save(new Ship("Lod5", "Karla", CargoType.FUELS));
       Ship ship6 = shipService.save(new Ship("Lod6", "Jana", CargoType.FOOD));
       Ship ship7 = shipService.save(new Ship("Lod7", "Jana", CargoType.FOOD));

       Schedule schedule1 = scheduleService.save(new Schedule("02.02.2022 13:00", "03.02.2022 18:00", StateOfShip.HARBER, ship1));
       Schedule schedule2 = scheduleService.save(new Schedule("06.02.2022 13:00", "08.02.2022 12:00", StateOfShip.GONE, ship2));
       Schedule schedule3 = scheduleService.save(new Schedule("04.02.2022 13:00", "06.02.2022 14:00", StateOfShip.GONE, ship3));
       Schedule schedule4 = scheduleService.save(new Schedule("10.02.2022 11:00", "15.02.2022 18:00", StateOfShip.GONE, ship4));
       Schedule schedule5 = scheduleService.save(new Schedule("01.01.2022 12:00", "02.01.2022 19:00", StateOfShip.ARRIVING, ship5));
       Schedule schedule6 = scheduleService.save(new Schedule("03.01.2022 13:00", "03.01.2022 22:00", StateOfShip.ARRIVING, ship6));
       Schedule schedule7 = scheduleService.save(new Schedule("02.02.2022 14:00", "03.02.2022 23:30", StateOfShip.HARBER, ship7));

       Cargo cargo1 = cargoService.save(new Cargo(55100f, "Brambory", "Poznamka", new BigDecimal(10.0), "Brambory s.r.o",ship6));
       Cargo cargo2 = cargoService.save(new Cargo(155f, "Jahody", "Poznamka", new BigDecimal(10.0), "StrawSaw", ship7));
       Cargo cargo3 = cargoService.save(new Cargo(750f, "Uran", "Poznamka", new BigDecimal(10.0), "DangerStone", ship3));
       Cargo cargo4 = cargoService.save(new Cargo(3800f, "Wish", "Poznamka", new BigDecimal(10.0), "Wish", ship4));
       Cargo cargo5 = cargoService.save(new Cargo(9500f, "Ropa", "Poznamka", new BigDecimal(10.0), "Aladin", ship5));
       Cargo cargo6 = cargoService.save(new Cargo(2500f, "Brambory", "Poznamka", new BigDecimal(10.0), "Potaty", ship6));
       Cargo cargo7 = cargoService.save(new Cargo(8500f, "Brambory", "Poznamka", new BigDecimal(10.0), "TakeIt", ship6));
       Cargo cargo8 = cargoService.save(new Cargo(10500f, "Koně", "Poznamka", new BigDecimal(10.0), "Speeder", ship1));
       Cargo cargo9 = cargoService.save(new Cargo(1500f, "Krávy", "Poznamka", new BigDecimal(10.0), "Speeder", ship2));
       Cargo cargo10 = cargoService.save(new Cargo(15500f, "Slepice", "Poznamka", new BigDecimal(10.0), "Speeder", ship2));

       Worker worker1 = workerService.save(new Worker("prvni1@email.cz", "heslo", "Jan", "Nosek1", "1112223331", null));
       Worker worker2 = workerService.save(new Worker("prvni2@email.cz", "heslo", "Jan", "Nosek2", "1112223332", null));
       Worker worker3 = workerService.save(new Worker("prvni3@email.cz", "heslo", "Jan", "Nosek3", "1112223333", null));
       Worker worker4 = workerService.save(new Worker("prvni4@email.cz", "heslo", "Jan", "Nosek4", "1112223334", null));
       Worker worker5 = workerService.save(new Worker("prvni5@email.cz", "heslo", "Jan", "Nosek5", "1112223335", null));
       Worker worker6 = workerService.save(new Worker("prvni6@email.cz", "heslo", "Jan", "Nosek6", "1112223336", null));
       Worker worker7 = workerService.save(new Worker("prvni7@email.cz", "heslo", "Jan", "Nosek7", "1112223337", null));

       worker1.setBoss(worker2);
       worker3.setBoss(worker2);
       worker2.setBoss(worker4);
       worker6.setBoss(worker4);
       worker7.setBoss(worker4);
       worker5.setBoss(worker4);
       worker2.setRole(Role.BOSS);
       worker6.setRole(Role.ADMIN);
       worker4.setRole(Role.ADMIN);

       workerService.save(worker1);
       workerService.save(worker3);
       workerService.save(worker2);
       workerService.save(worker6);
       workerService.save(worker7);
       workerService.save(worker5);
   }

}
