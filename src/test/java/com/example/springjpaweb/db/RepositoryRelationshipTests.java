package com.example.springjpaweb.db;

import com.example.springjpaweb.entity.Cargo;
import com.example.springjpaweb.entity.Ship;
import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.enums.CargoType;
import com.example.springjpaweb.enums.Role;
import com.example.springjpaweb.repository.CargoRepository;
import com.example.springjpaweb.repository.ShipRepository;
import com.example.springjpaweb.repository.WorkerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryRelationshipTests {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Test
    public void testWorkerRepositoryWorking() {
        Ship ship1 = shipRepository.save(new Ship("Lod1", "Linda", CargoType.ANIMALS));
        Ship ship2 = shipRepository.save(new Ship("Lod2", "Linda", CargoType.ANIMALS));
        Ship ship3 = shipRepository.save(new Ship("Lod3", "Linda", CargoType.ANIMALS));

        Cargo cargo1 = cargoRepository.save(new Cargo(5f, "Brambory1", "Poznamka", new BigDecimal(10), ship1));
        Cargo cargo2 = cargoRepository.save(new Cargo(5f, "Brambory2", "Poznamka", new BigDecimal(10), ship2));
        Cargo cargo3 = cargoRepository.save(new Cargo(5f, "Brambory3", "Poznamka", new BigDecimal(10), ship2));
        Cargo cargo4 = cargoRepository.save(new Cargo(5f, "Brambory4", "Poznamka", new BigDecimal(10), ship1));
        Cargo cargo5 = cargoRepository.save(new Cargo(5f, "Brambory5", "Poznamka", new BigDecimal(10)));
        Cargo cargo6 = cargoRepository.save(new Cargo(5f, "Brambory6", "Poznamka", new BigDecimal(10)));
        List<Cargo> cargos = Arrays.asList(cargo5, cargo6);

        Worker worker1 = workerRepository.save(new Worker("prvni1@email.cz", "heslo", "Jan", "Nosek1", "1112223331", null));
        Worker worker2 = workerRepository.save(new Worker("prvni2@email.cz", "heslo", "Jan", "Nosek2", "1112223332", null));
        Worker worker3 = workerRepository.save(new Worker("prvni3@email.cz", "heslo", "Jan", "Nosek3", "1112223333", null));
        Worker worker4 = workerRepository.save(new Worker("prvni4@email.cz", "heslo", "Jan", "Nosek4", "1112223334", null));
        Worker worker5 = workerRepository.save(new Worker("prvni5@email.cz", "heslo", "Jan", "Nosek5", "1112223335", null));

        worker1.setBoss(worker2);
        worker3.setBoss(worker2);
        worker2.setBoss(worker4);
        worker2.setRole(Role.BOSS);

        workerRepository.save(worker1);
        workerRepository.save(worker3);
        workerRepository.save(worker2);

        assertThat(workerRepository.findAll()).isInstanceOf(List.class);
    }
}
