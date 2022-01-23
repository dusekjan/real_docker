package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.Cargo;
import com.example.springjpaweb.entity.Schedule;
import com.example.springjpaweb.entity.Ship;
import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.service.CargoService;
import com.example.springjpaweb.service.ScheduleService;
import com.example.springjpaweb.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GeneralController {

    private final ScheduleService scheduleService;
    private final WorkerService workerService;
    private final CargoService cargoService;

    public GeneralController(ScheduleService scheduleService, WorkerService workerService, CargoService cargoService) {
        this.scheduleService = scheduleService;
        this.workerService = workerService;
        this.cargoService = cargoService;
    }

    @GetMapping(value = {"/", "/uvod"})
    public String getHomePage(){
        return "uvod";
    }

    @GetMapping("/rozcestnik")
    public String getSignPostPage() {
        return "rozcestnik";
    }

    @GetMapping("/harmonogram")
    public String GetSchedulePage(Model model) {
        List<Schedule> completeSchedule = scheduleService.getSchedulesAndShips();

        model.addAttribute("harmData", completeSchedule);

        return "harmonogram";
    }

    @GetMapping("/editace-lodi")
    public String getEditShipPage(Model model) {
        List<Schedule> completeSchedule = scheduleService.getSchedulesAndShips();

        model.addAttribute("shipData", completeSchedule);

        return "editace";
    }


    @GetMapping("/editace-zamestnancu")
    public String getEditEmployeePage(Model model) {
        List<Worker> workerList = workerService.getAll();
        System.out.println(workerList);

        model.addAttribute("workers", workerList);

        return "evidenceZamestnancu";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "error";
    }


//    @GetMapping("/")
//    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting";
//    }

}
