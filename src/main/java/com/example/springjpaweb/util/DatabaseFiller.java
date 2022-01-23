package com.example.springjpaweb.util;

import com.example.springjpaweb.entity.Cargo;
import com.example.springjpaweb.entity.Schedule;
import com.example.springjpaweb.entity.Ship;
import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.enums.CargoType;
import com.example.springjpaweb.enums.Role;
import com.example.springjpaweb.enums.StateOfShip;
import com.example.springjpaweb.service.*;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DatabaseFiller {

    private final StudentService studentService;
    private final ShipService shipService;
    private final CargoService cargoService;
    private final ScheduleService scheduleService;
    private final WorkerService workerService;

    public DatabaseFiller(StudentService studentService, ShipService shipService, CargoService cargoService, ScheduleService scheduleService, WorkerService workerService) {
        this.studentService = studentService;
        this.shipService = shipService;
        this.cargoService = cargoService;
        this.scheduleService = scheduleService;
        this.workerService = workerService;

        fillDatabase();
    }

   public void fillDatabase(){
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

       Worker worker1 = workerService.save(new Worker("prvni1@email.cz", "MOJE HESLOO", "Jan", "Nosek1", "1112223331", null));
       Worker worker2 = workerService.save(new Worker("prvni2@email.cz", "heslo", "Jan", "Nosek2", "1112223332", null));
       Worker worker3 = workerService.save(new Worker("prvni3@email.cz", "heslo", "Jan", "Nosek3", "1112223333", null));
       Worker worker4 = workerService.save(new Worker("prvni4@email.cz", "hello", "Jan", "Nosek4", "1112223334", null));
       Worker worker5 = workerService.save(new Worker("prvni5@email.cz", "heslo", "Jan", "Nosek5", "1112223335", null));
       Worker worker6 = workerService.save(new Worker("prvni6@email.cz", "heslo", "Jan", "Nosek6", "1112223336", null));
       Worker worker7 = workerService.save(new Worker("prvni7@email.cz", "heslo", "Jan", "Nosek7", "1112223337", null));

       worker1.setBoss(worker2);
       worker3.setBoss(worker2);
       worker2.setBoss(worker4);
       worker6.setBoss(worker4);
       worker7.setBoss(worker4);
       worker5.setBoss(worker4);

       //automaticky se dava Role.WORKER
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
