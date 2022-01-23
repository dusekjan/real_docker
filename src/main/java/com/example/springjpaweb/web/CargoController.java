package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.*;
import com.example.springjpaweb.service.*;
import com.example.springjpaweb.web.errors.ErrorResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CargoController {
    private final StudentService studentService;
    private final CargoService cargoService;

    public CargoController(StudentService studentService, CargoService cargoService) {
        this.studentService = studentService;
        this.cargoService = cargoService;
    }

    @GetMapping("/cargos/{id}")
    public List<Cargo> getCargosOfShip(@PathVariable long id){
        return cargoService.getCargosOfShip(id);
    }

    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) {
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
