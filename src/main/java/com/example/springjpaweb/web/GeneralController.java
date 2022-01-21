package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.Schedule;
import com.example.springjpaweb.service.ScheduleService;
import com.example.springjpaweb.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GeneralController {

    private final ScheduleService scheduleService;

    public GeneralController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
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
    public String getEditShipPage() {
        return "editace";
    }

    @GetMapping("/editace-zamestnancu")
    public String getEditEmployeePage() {
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
