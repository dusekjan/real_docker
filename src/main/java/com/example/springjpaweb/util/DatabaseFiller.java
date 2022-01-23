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

    public void fillDatabase() {
        
        Ship ship1 = shipService.save(new Ship("Wizard", "Mediterranean Shipping", CargoType.ANIMALS));
        Ship ship2 = shipService.save(new Ship("Delos", "Pacific International Lines", CargoType.FOOD));
        Ship ship3 = shipService.save(new Ship("Britannia", "X-Press Feeders", CargoType.VEHICLES));
        Ship ship4 = shipService.save(new Ship("Queen Elizabeth", "Pacific International Lines", CargoType.DRYBULK));
        Ship ship5 = shipService.save(new Ship("Saga", "Transworld Group", CargoType.FUELS));
        Ship ship6 = shipService.save(new Ship("Aidasol", "Mediterranean Shipping", CargoType.OTHERS));
        Ship ship7 = shipService.save(new Ship("Kennedy", "X-Press Feeders", CargoType.FOOD));
        Ship ship8 = shipService.save(new Ship("USS Nimitz", "Transworld Group", CargoType.MACHINERY));
        Ship ship9 = shipService.save(new Ship("LUS Comfort", "X-Press Feeders", CargoType.VEHICLES));
        Ship ship10 = shipService.save(new Ship("Sea Watch", "Pacific International Lines", CargoType.CHEMICAL));
        Ship ship11 = shipService.save(new Ship("Aidaluna", "X-Press Feeders", CargoType.MACHINERY));
        Ship ship12 = shipService.save(new Ship("Aidamar", "Mediterranean Shipping", CargoType.FUELS));
        Ship ship13 = shipService.save(new Ship("Alex Von Hundebold", "X-Press Feeders", CargoType.FOOD));
        Ship ship14 = shipService.save(new Ship("Empire state", "Transworld Group", CargoType.VEHICLES));
        Ship ship15 = shipService.save(new Ship("Diamond princess", "X-Press Feeders", CargoType.ANIMALS));
        Ship ship16 = shipService.save(new Ship("Polarstern", "Pacific International Lines", CargoType.FUELS));
        Ship ship17 = shipService.save(new Ship("Luminosa", "SM Line", CargoType.CHEMICAL));
        Ship ship18 = shipService.save(new Ship("Favolosa", "Mediterranean Shipping", CargoType.DRYBULK));
        Ship ship19 = shipService.save(new Ship("Maud", "Transworld Group", CargoType.LIQUIDBULK));
        Ship ship20 = shipService.save(new Ship("Curiosity", "Pacific International Lines", CargoType.FUELS));

        Schedule schedule1 = scheduleService.save(new Schedule("25.01.2022 09:00", "31.01.2022 17:00", StateOfShip.HARBER, ship1));
        Schedule schedule2 = scheduleService.save(new Schedule("06.01.2022 13:00", "08.01.2022 16:30", StateOfShip.GONE, ship2));
        Schedule schedule3 = scheduleService.save(new Schedule("20.01.2022 16:50", "21.01.2022 14:00", StateOfShip.GONE, ship3));
        Schedule schedule4 = scheduleService.save(new Schedule("26.01.2022 12:00", "01.02.2022 15:30", StateOfShip.HARBER, ship4));
        Schedule schedule5 = scheduleService.save(new Schedule("01.02.2022 22:00", "07.02.2022 19:00", StateOfShip.ARRIVING, ship5));
        Schedule schedule6 = scheduleService.save(new Schedule("31.12.2021 16:20", "11.01.2022 17:00", StateOfShip.GONE, ship6));
        Schedule schedule7 = scheduleService.save(new Schedule("19.01.2022 17:15", "28.01.2022 13:30", StateOfShip.HARBER, ship7));
        Schedule schedule8 = scheduleService.save(new Schedule("26.01.2022 14:40", "28.01.2022 17:55", StateOfShip.HARBER, ship8));
        Schedule schedule9 = scheduleService.save(new Schedule("06.02.2022 13:00", "08.02.2022 12:00", StateOfShip.ARRIVING, ship9));
        Schedule schedule10 = scheduleService.save(new Schedule("02.01.2022 16:00", "04.01.2022 17:30", StateOfShip.GONE, ship10));
        Schedule schedule11 = scheduleService.save(new Schedule("13.01.2022 08:00", "14.01.2022 18:00", StateOfShip.GONE, ship11));
        Schedule schedule12 = scheduleService.save(new Schedule("18.01.2022 19:15", "29.01.2022 07:20", StateOfShip.HARBER, ship12));
        Schedule schedule13 = scheduleService.save(new Schedule("06.01.2022 12:30", "07.01.2022 12:50", StateOfShip.GONE, ship13));
        Schedule schedule14 = scheduleService.save(new Schedule("25.01.2022 16:20", "30.01.2022 23:30", StateOfShip.HARBER, ship14));
        Schedule schedule15 = scheduleService.save(new Schedule("02.02.2022 15:00", "03.02.2022 18:00", StateOfShip.ARRIVING, ship15));
        Schedule schedule16 = scheduleService.save(new Schedule("05.01.2022 06:20", "08.01.2022 04:15", StateOfShip.GONE, ship16));
        Schedule schedule17 = scheduleService.save(new Schedule("04.02.2022 12:00", "06.02.2022 14:00", StateOfShip.ARRIVING, ship17));
        Schedule schedule18 = scheduleService.save(new Schedule("28.12.2021 11:00", "31.12.2021 15:40", StateOfShip.GONE, ship18));
        Schedule schedule19 = scheduleService.save(new Schedule("18.01.2022 14:40", "30.01.2022 19:50", StateOfShip.HARBER, ship19));
        Schedule schedule20 = scheduleService.save(new Schedule("03.02.2022 08:30", "12.02.2022 16:00", StateOfShip.ARRIVING, ship20));

        Cargo cargo1 = cargoService.save(new Cargo(5510f, "Ovce", "Žádná", new BigDecimal(10000.0), "Big farm company", ship1));
        Cargo cargo2 = cargoService.save(new Cargo(15500f, "Krávy", "Žádná", new BigDecimal(50000.0), "Big farm company", ship1));
        Cargo cargo3 = cargoService.save(new Cargo(2500f, "Slepice", "Nutnost jeřábu na klece", new BigDecimal(6500.0), "White farm", ship1));
        Cargo cargo4 = cargoService.save(new Cargo(50000f, "Brambory", "Žádná", new BigDecimal(120000.0), "Big farm company", ship2));
        Cargo cargo5 = cargoService.save(new Cargo(48000f, "Mrkev", "Žádná", new BigDecimal(80000.0), "White farm", ship2));
        Cargo cargo6 = cargoService.save(new Cargo(20000f, "Škoda Octavia", "Kastle", new BigDecimal(15000000.0), "Škoda auto", ship3));
        Cargo cargo7 = cargoService.save(new Cargo(5000f, "Díl 8560", "Dveře na Octavii", new BigDecimal(6000000.0), "Škoda auto", ship3));
        Cargo cargo8 = cargoService.save(new Cargo(18000f, "Škoda Octavia", "Kastle", new BigDecimal(12000000.0), "Škoda auto", ship3));
        Cargo cargo9 = cargoService.save(new Cargo(4500f, "Díl 9560", "Dveře na fabii", new BigDecimal(450000.0), "Škoda auto", ship3));
        Cargo cargo10 = cargoService.save(new Cargo(5000f, "Obilí", "Nutnost nákladního vozu", new BigDecimal(55000.0), "Brambory s.r.o", ship4));
        Cargo cargo11 = cargoService.save(new Cargo(18000f, "Uhlí", "Nutnost jeřábu", new BigDecimal(80000.0), "Brambory s.r.o", ship4));
        Cargo cargo12 = cargoService.save(new Cargo(25000f, "Železná ruda", "Nutnost jeřábu", new BigDecimal(65000.0), "Gone down s. r. o.", ship4));
        Cargo cargo13 = cargoService.save(new Cargo(75000f, "Biodiesel", "Žádná", new BigDecimal(20000000.0), "Power Shell", ship5));
        Cargo cargo14 = cargoService.save(new Cargo(60000f, "Diesel", "Žádná", new BigDecimal(18500000.0), "Power Shell", ship5));
        Cargo cargo15 = cargoService.save(new Cargo(55000f, "Gasoline", "Výbušný produkt", new BigDecimal(6000000.0), "Power Shell", ship5));
        Cargo cargo16 = cargoService.save(new Cargo(15000f, "Ledničky", "Nutnost jeřábu", new BigDecimal(18000000.0), "Electronix", ship6));
        Cargo cargo17 = cargoService.save(new Cargo(25500f, "Mrazáky", "Nutnost jeřábu", new BigDecimal(15000000.0), "Electronix", ship6));
        Cargo cargo18 = cargoService.save(new Cargo(2500f, "Brambory", "Žádná", new BigDecimal(150000.0), "TakeIt", ship7));
        Cargo cargo19 = cargoService.save(new Cargo(8500f, "Papriky", "Žádná", new BigDecimal(250000.0), "TakeIt", ship7));
        Cargo cargo20 = cargoService.save(new Cargo(10500f, "Jablka", "Žádná", new BigDecimal(563000.0), "Speeder", ship7));
        Cargo cargo21 = cargoService.save(new Cargo(55000f, "Bagry", "Žádná", new BigDecimal(250000000.0), "Gone down s. r. o.", ship8));
        Cargo cargo22 = cargoService.save(new Cargo(155000f, "Náklaďáky", "Žádná", new BigDecimal(80000000.0), "Gone down s. r. o.", ship8));
        Cargo cargo23 = cargoService.save(new Cargo(7500f, "Díl 8B50", "Náhradní korba", new BigDecimal(120000.0), "Gone down s. r. o.", ship8));
        Cargo cargo24 = cargoService.save(new Cargo(3500f, "Díl 8E236", "Náhradní lžíce", new BigDecimal(150000.0), "Gone down s. r. o.", ship8));
        Cargo cargo25 = cargoService.save(new Cargo(95000f, "Audi A1", "Žádná", new BigDecimal(10520000.0), "Audi", ship9));
        Cargo cargo26 = cargoService.save(new Cargo(25000f, "Audi A3", "Žádná", new BigDecimal(15654000.0), "Audi", ship9));
        Cargo cargo27 = cargoService.save(new Cargo(85000f, "Audi Q2", "Žádná", new BigDecimal(18774000.0), "Audi", ship9));
        Cargo cargo28 = cargoService.save(new Cargo(2500f, "Benzen", "Nebezpečný náklad", new BigDecimal(150000.0), "Chemicals RNA", ship10));
        Cargo cargo29 = cargoService.save(new Cargo(1500f, "Rtuť", "Nebezpečný náklad", new BigDecimal(240000.0), "Chemicals RNA", ship10));
        Cargo cargo30 = cargoService.save(new Cargo(35500f, "Bagry C8", "Žádná", new BigDecimal(10000000.0), "Oil company", ship11));
        Cargo cargo31 = cargoService.save(new Cargo(55100f, "Bagry C9", "Žádná", new BigDecimal(30000000.0), "Oil company", ship11));
        Cargo cargo32 = cargoService.save(new Cargo(155000f, "Pneumatiky C8", "Nutnost jeřábu", new BigDecimal(12500000.0), "Oil company", ship11));
        Cargo cargo33 = cargoService.save(new Cargo(75000f, "Ropa", "Nutnost jeřábu", new BigDecimal(180000.0), "OMV", ship12));
        Cargo cargo34 = cargoService.save(new Cargo(38000f, "Gasoline", "Výbušný náklad", new BigDecimal(170000.0), "OMV", ship12));
        Cargo cargo35 = cargoService.save(new Cargo(95000f, "Banány", "Žádná", new BigDecimal(500500.0), "Penny market", ship13));
        Cargo cargo36 = cargoService.save(new Cargo(25000f, "Pomeranče", "Žádná", new BigDecimal(874000.0), "Lidl", ship13));
        Cargo cargo37 = cargoService.save(new Cargo(85000f, "BMW e46", "Nutnost jeřábu", new BigDecimal(14500000.0), "BMW", ship14));
        Cargo cargo38 = cargoService.save(new Cargo(105000f, "BMW e36", "Nutnost jeřábu", new BigDecimal(18605000.0), "BMW", ship14));
        Cargo cargo39 = cargoService.save(new Cargo(15000f, "Krávy", "Žádná", new BigDecimal(650000.0), "MyfarmOK", ship15));
        Cargo cargo40 = cargoService.save(new Cargo(8500f, "Slepice", "Žádná", new BigDecimal(563000.0), "MyfarmOK", ship15));
        Cargo cargo41 = cargoService.save(new Cargo(5500f, "Kozy", "Žádná", new BigDecimal(75000.0), "MyfarmOK", ship15));
        Cargo cargo42 = cargoService.save(new Cargo(17500f, "Koně", "Vzácný druh, nutnost opatrnosti", new BigDecimal(54000500.0), "MyfarmOK", ship15));
        Cargo cargo43 = cargoService.save(new Cargo(75000f, "Biodiesel", "Žádná", new BigDecimal(1800000.0), "DangerStone", ship16));
        Cargo cargo44 = cargoService.save(new Cargo(38000f, "Diesel", "Nebezpečný materiál", new BigDecimal(109000.0), "DangerStone", ship16));
        Cargo cargo45 = cargoService.save(new Cargo(9500f, "Fuel oil", "Vzácný náklad", new BigDecimal(25000000.0), "Aladin", ship16));
        Cargo cargo46 = cargoService.save(new Cargo(2500f, "Oxid dusný", "Nebezpečný materiál", new BigDecimal(150000.0), "UHK", ship17));
        Cargo cargo47 = cargoService.save(new Cargo(8500f, "Guma", "Žádná", new BigDecimal(185000.0), "Farmaceutics s. r. o.", ship17));
        Cargo cargo48 = cargoService.save(new Cargo(1050f, "Lepidlo a456", "Nebezpečný materiál", new BigDecimal(2400000.0), "Farmaceutics s. r. o.", ship17));
        Cargo cargo49 = cargoService.save(new Cargo(5500f, "Cukr", "Žádná", new BigDecimal(100000.0), "Lidl", ship18));
        Cargo cargo50 = cargoService.save(new Cargo(6500f, "Sůl", "Žádná", new BigDecimal(120000.0), "Lidl", ship18));
        Cargo cargo51 = cargoService.save(new Cargo(5500f, "Topný olej", "Žádná", new BigDecimal(180000.0), "Brambory s.r.o", ship19));
        Cargo cargo52 = cargoService.save(new Cargo(8470f, "Rostliný olej", "Žádná", new BigDecimal(56000.0), "Brambory s.r.o", ship19));
        Cargo cargo53 = cargoService.save(new Cargo(7500f, "Rope", "Nutnost jeřábu", new BigDecimal(180000.0), "DangerStone", ship20));
        Cargo cargo54 = cargoService.save(new Cargo(3800f, "Diesel", "Poznamka", new BigDecimal(785000.0), "DangerStone", ship20));
        Cargo cargo55 = cargoService.save(new Cargo(9500f, "Biodiesel", "Nebezpečný materiál", new BigDecimal(457500.0), "DangerStone", ship20));

        Worker worker1 = workerService.save(new Worker("worker1@email.cz", "heslo", "Jan", "Novák", "605854159", null));
        Worker worker2 = workerService.save(new Worker("worker2@email.cz", "heslo", "Petr", "Mladý", "745951236", null));
        Worker worker3 = workerService.save(new Worker("worker3@email.cz", "heslo", "Iva", "Horká", "621159085", null));
        Worker worker4 = workerService.save(new Worker("worker4@email.cz", "heslo", "Patrik", "Polívka", "789425621", null));
        Worker worker5 = workerService.save(new Worker("worker5@email.cz", "heslo", "Jindřich", "Skotal", "689999520", null));
        Worker worker6 = workerService.save(new Worker("worker6@email.cz", "heslo", "Martina", "Sedláčková", "705520236", null));
        Worker worker7 = workerService.save(new Worker("worker7@email.cz", "heslo", "Jana", "Horská", "654145145", null));
        Worker worker8 = workerService.save(new Worker("worker8@email.cz", "heslo", "Josef", "Oblý", "809621589", null));
        Worker worker9 = workerService.save(new Worker("worker9@email.cz", "heslo", "Jan", "Potměšil", "874541002", null));
        Worker worker10 = workerService.save(new Worker("worker10@email.cz", "heslo", "Ivana", "Červená", "600200523", null));
        Worker worker11 = workerService.save(new Worker("worker11@email.cz", "heslo", "Jirka", "Blažek", "841520000", null));
        Worker worker12 = workerService.save(new Worker("worker12@email.cz", "heslo", "Monika", "Blašková", "850369369", null));
        Worker worker13 = workerService.save(new Worker("worker13@email.cz", "heslo", "Ondřej", "Veselý", "546587478", null));
        Worker worker14 = workerService.save(new Worker("worker14@email.cz", "heslo", "Lenka", "Nosková", "650000259", null));
        Worker worker15 = workerService.save(new Worker("worker15@email.cz", "heslo", "Gustav", "Ovčák", "600300500", null));
        Worker worker16 = workerService.save(new Worker("worker16@email.cz", "heslo", "Gabriela", "Nekukalová", "800400952", null));
        Worker worker17 = workerService.save(new Worker("worker17@email.cz", "heslo", "Simona", "Kulatá", "740742852", null));
        Worker worker18 = workerService.save(new Worker("worker18@email.cz", "heslo", "Jana", "Bílá", "600258967", null));
        Worker worker19 = workerService.save(new Worker("worker19@email.cz", "heslo", "František", "Kličpera", "602548987", null));
        Worker worker20 = workerService.save(new Worker("worker20@email.cz", "heslo", "Hana", "Rakousková", "605743521", null));
        Worker worker21 = workerService.save(new Worker("boss1@email.cz", "heslo", "Dominik", "Krátký", "902520541", null));
        Worker worker22 = workerService.save(new Worker("boss2@email.cz", "heslo", "Jana", "Neveselá", "652000300", null));
        Worker worker23 = workerService.save(new Worker("boss3@email.cz", "heslo", "Božetěch", "Němec", "650478541", null));
        Worker worker24 = workerService.save(new Worker("boss4@email.cz", "heslo", "Rozárie", "Trnková", "784458512", null));
        Worker worker25 = workerService.save(new Worker("admin1@email.cz", "heslo", "Pavel", "Blažek", "605259584", null));
        Worker worker26 = workerService.save(new Worker("admin2@email.cz", "heslo", "Helena", "Fousková", "750006987", null));

        worker1.setBoss(worker21);
        worker2.setBoss(worker21);
        worker3.setBoss(worker21);
        worker4.setBoss(worker21);
        worker5.setBoss(worker21);
        worker6.setBoss(worker22);
        worker7.setBoss(worker22);
        worker8.setBoss(worker22);
        worker9.setBoss(worker22);
        worker10.setBoss(worker22);
        worker11.setBoss(worker23);
        worker12.setBoss(worker23);
        worker13.setBoss(worker23);
        worker14.setBoss(worker23);
        worker15.setBoss(worker23);
        worker16.setBoss(worker24);
        worker17.setBoss(worker24);
        worker18.setBoss(worker24);
        worker19.setBoss(worker24);
        worker20.setBoss(worker24);
        worker21.setBoss(worker25);
        worker22.setBoss(worker25);
        worker23.setBoss(worker25);
        worker24.setBoss(worker25);
        worker25.setBoss(worker26);

        worker21.setRole(Role.BOSS);
        worker22.setRole(Role.BOSS);
        worker23.setRole(Role.BOSS);
        worker24.setRole(Role.BOSS);
        worker25.setRole(Role.ADMIN);
        worker26.setRole(Role.ADMIN);

        workerService.save(worker1);
        workerService.save(worker2);
        workerService.save(worker3);
        workerService.save(worker4);
        workerService.save(worker5);
        workerService.save(worker6);
        workerService.save(worker7);
        workerService.save(worker8);
        workerService.save(worker9);
        workerService.save(worker10);
        workerService.save(worker11);
        workerService.save(worker12);
        workerService.save(worker13);
        workerService.save(worker14);
        workerService.save(worker15);
        workerService.save(worker16);
        workerService.save(worker17);
        workerService.save(worker18);
        workerService.save(worker19);
        workerService.save(worker20);
        workerService.save(worker21);
        workerService.save(worker22);
        workerService.save(worker23);
        workerService.save(worker24);
        workerService.save(worker25);
        workerService.save(worker26);
    }

}
