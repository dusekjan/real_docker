package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.Cargo;
import com.example.springjpaweb.entity.Ship;
import com.example.springjpaweb.entity.Student;
import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.enums.CargoType;
import com.example.springjpaweb.enums.Role;
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


        Ship ship1 = shipService.save(new Ship("Lod1", "Linda", CargoType.ANIMALS));
        Ship ship2 = shipService.save(new Ship("Lod2", "Linda", CargoType.ANIMALS));
        Ship ship3 = shipService.save(new Ship("Lod3", "Linda", CargoType.ANIMALS));


        Cargo cargo1 = cargoService.save(new Cargo(5f, "Brambory1", "Poznamka", new BigDecimal(10), ship1));
        Cargo cargo2 = cargoService.save(new Cargo(5f, "Brambory2", "Poznamka", new BigDecimal(10), ship2));
        Cargo cargo3 = cargoService.save(new Cargo(5f, "Brambory3", "Poznamka", new BigDecimal(10), ship2));
        Cargo cargo4 = cargoService.save(new Cargo(5f, "Brambory4", "Poznamka", new BigDecimal(10), ship1));
        Cargo cargo5 = cargoService.save(new Cargo(5f, "Brambory5", "Poznamka", new BigDecimal(10)));
        Cargo cargo6 = cargoService.save(new Cargo(5f, "Brambory6", "Poznamka", new BigDecimal(10)));
        List<Cargo> cargos = Arrays.asList(cargo5, cargo6);

        Worker worker1 = workerService.save(new Worker("prvni1@email.cz", "heslo", "Jan", "Nosek1", "1112223331", null));
        Worker worker2 = workerService.save(new Worker("prvni2@email.cz", "heslo", "Jan", "Nosek2", "1112223332", null));
        Worker worker3 = workerService.save(new Worker("prvni3@email.cz", "heslo", "Jan", "Nosek3", "1112223333", null));
        Worker worker4 = workerService.save(new Worker("prvni4@email.cz", "heslo", "Jan", "Nosek4", "1112223334", null));
        Worker worker5 = workerService.save(new Worker("prvni5@email.cz", "heslo", "Jan", "Nosek5", "1112223335", null));

        worker1.setBoss(worker2);
        worker3.setBoss(worker2);
        worker2.setBoss(worker4);
        worker2.setRole(Role.BOSS);

        workerService.save(worker1);
        workerService.save(worker3);
        workerService.save(worker2);

//
//        Student student1 = service.save(new Student("Jan", "Novák"));
//        Student student2 =  service.save(new Student("Jana", "Nováková"));
//
//        Ship ship1 = service2.save(new Ship("Lod1", "Lodenice", student1));
//        Ship ship2 = service2.save(new Ship("Lod2", "Lodenice2"));
//        Ship ship3 = service2.save(new Ship("Lod3", "Lodenice3"));
//        Ship ship4 = service2.save(new Ship("Lod4", "Linda"));
//        Ship ship5 = service2.save(new Ship("Lod6", "Linda"));
//        Ship ship6 = service2.save(new Ship("Lod4", "Linda"));
//        Ship ship7 = service2.save(new Ship("Lod7", "Linda"));
//
//        ship3.setStudent(student2);
//
//        service2.save(ship3);
//
//        List<Ship> lindaShips = service2.getAllLinda();
//        System.out.println(lindaShips);
//
//        for (Ship ship : lindaShips) {
//            System.out.println(ship.getName() + " " + ship.getCompany());
//        }
//
//        System.out.println(service2.findMultipleShipsCompanyName("Linda", "Lod4").toArray());
//        System.out.println(service2.findMultipleShipsCompanyName("Linda", "Lod4").toArray().length);


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

}
