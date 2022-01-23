package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.Cargo;
import com.example.springjpaweb.entity.Student;
import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.service.CargoService;
import com.example.springjpaweb.service.StudentService;
import com.example.springjpaweb.service.WorkerService;
import com.example.springjpaweb.web.errors.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class WorkerController {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/worker/{id}")
    public Optional<Worker> getWorker(@PathVariable long id){
        System.out.println("Tady je problem");
        return workerService.findById(id);
    }

    @PostMapping("/worker")
    public Worker createStudent(@RequestBody Worker worker) {
        System.out.println(worker);
        return workerService.save(worker);
    }

    @PutMapping("/worker/{id}")
    public Worker updateWorker(@PathVariable long id, @RequestBody Worker workerData){
//        student.setId(id);
        System.out.println("WORKERDATAAAA: " + workerData);
        Optional<Worker> workerFromDb = workerService.findById(id);

        if (workerFromDb.isPresent()) {
            Worker worker = workerFromDb.get();

            worker.setFirstName(workerData.getFirstName());
            worker.setSureName(workerData.getSureName());
            worker.setEmail(workerData.getEmail());
            worker.setPhone(workerData.getPhone());
            worker.setRole(workerData.getRole());

            return workerService.save(worker);

        } else {
            return new Worker();
        }
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
