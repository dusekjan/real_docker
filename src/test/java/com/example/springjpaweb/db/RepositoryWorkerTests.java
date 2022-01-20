package com.example.springjpaweb.db;


import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.repository.WorkerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryWorkerTests {

    @Autowired
    private WorkerRepository workerRepository;

    @Test
    public void testWorkerRepositoryWorking() {

        Worker worker1 = workerRepository.save(new Worker("prvni1@email.cz", "heslo", "Jan", "Nosek1", "1112223331", null));
        Worker worker2 = workerRepository.save(new Worker("prvni2@email.cz", "heslo", "Jan", "Nosek2", "1112223332", null));
        Worker worker3 = workerRepository.save(new Worker("prvni3@email.cz", "heslo", "Jan", "Nosek3", "1112223333", null));
        Worker worker4 = workerRepository.save(new Worker("prvni4@email.cz", "heslo", "Jan", "Nosek4", "1112223334", null));
        Worker worker5 = workerRepository.save(new Worker("prvni5@email.cz", "heslo", "Jan", "Nosek5", "1112223335", null));

    }
}
