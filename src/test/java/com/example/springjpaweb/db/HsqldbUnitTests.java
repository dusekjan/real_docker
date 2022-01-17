package com.example.springjpaweb.db;


import com.example.springjpaweb.entity.Student;
import com.example.springjpaweb.repository.StudentRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HsqldbUnitTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void whenFindingStudentById_thenCorrect() {
        studentRepository.save(new Student("Jan", "Novotny"));
        assertThat(studentRepository.findById(1L)).isInstanceOf(Optional.class);
        System.out.println("Výstup: " + studentRepository.findById(1L));
    }

    @Test
    public void whenFindingAllStudents_thenCorrect() {
        studentRepository.save(new Student("John", "john@domain.com"));
        studentRepository.save(new Student("Julie", "julie@domain.com"));
        assertThat(studentRepository.findAll()).isInstanceOf(List.class);
        System.out.println("List: " + studentRepository.findAll());
    }
}
