package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Student;
import com.example.springjpaweb.repository.StudentRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository = repository;
    }

    public Optional<Student> findById(long id) {
        return repository.findById(id);
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    @PreAuthorize(("hasRole('SUPERADMIN')")) //ma smysl tohle psat na Beanach a ne na nejaky tride - Method security
    public void delete(long id){
        repository.deleteById(id);
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

}
