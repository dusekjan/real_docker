package com.example.springjpaweb.repository;

import com.example.springjpaweb.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {

}
