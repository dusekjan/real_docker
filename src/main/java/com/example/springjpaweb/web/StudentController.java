package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.Student;
import com.example.springjpaweb.service.StudentService;
import com.example.springjpaweb.web.errors.ErrorResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
        service.save(new Student("Jan", "Novák"));
        service.save(new Student("Jana", "Nováková"));
    }

    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) {
        return service.save(student);
    }

    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable long id, @RequestBody Student student){
       student.setId(id);
       return service.save(student);
   }

   @GetMapping("/students")
   public List<Student> getAllStudents(){
        return service.getAll();
   }

   @DeleteMapping("/student/{id}")
   public void deleteStudent(@PathVariable long id){
       service.delete(id);
   }


   @GetMapping("/student/{id}")
   public Optional<Student> getStudentById(@PathVariable long id){
       return service.findById(id);
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
