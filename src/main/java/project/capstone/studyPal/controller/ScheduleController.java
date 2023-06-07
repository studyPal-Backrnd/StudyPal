package project.capstone.studyPal.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.studyPal.data.models.Schedule;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.CreateScheduleRequest;
import project.capstone.studyPal.service.studyPalService.noteService.NoteService;
import project.capstone.studyPal.service.studyPalService.scheduleService.ScheduleService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/studypal/schedule")
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("create")
    public ResponseEntity<?> createSchedule(@Valid @RequestBody CreateScheduleRequest request){
        Schedule schedule = scheduleService.createASchedule(request);
        return ResponseEntity.status(HttpStatus.OK).body(schedule);
    }

    @GetMapping("")
    public ResponseEntity<?> getSchedules(){
        List<Schedule> foundSchedule = scheduleService.getAllSchedules();
        return ResponseEntity.status(HttpStatus.OK).body(foundSchedule);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getScheduleById(@Valid @PathVariable(value = "id") Long scheduleId){
        Schedule foundSchedule = scheduleService.getScheduleById(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(foundSchedule);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> createSchedule(@Valid @PathVariable(value = "id") Long scheduleId){
        scheduleService.deleteScheduleById(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
    }

}
