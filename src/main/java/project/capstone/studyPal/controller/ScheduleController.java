//package project.capstone.studyPal.controller;
//
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import project.capstone.studyPal.data.models.Schedule;
//import project.capstone.studyPal.dto.request.CreateScheduleRequest;
//import project.capstone.studyPal.service.studyPalService.scheduleService.ScheduleService;
//
//@AllArgsConstructor
//@RestController
//@RequestMapping("api/v1/studypal/schedule/")
//public class ScheduleController {
//    private final ScheduleService scheduleService;
//    @PostMapping("create")
//    public ResponseEntity<?> createSchedule(@Valid @RequestBody CreateScheduleRequest request){
//        Schedule schedule = scheduleService.createASchedule(request);
//        return ResponseEntity.status(HttpStatus.OK).body(schedule);
//    }
//
//    @GetMapping("get/{id}")
//    public ResponseEntity<?> getScheduleById(@Valid @PathVariable Long id){
//        Schedule schedule = scheduleService.getScheduleById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(schedule);
//    }
//
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<?> deleteScheduleById(@Valid @PathVariable Long id){
//        scheduleService.deleteScheduleById(id);
//        return ResponseEntity.status(HttpStatus.OK).body("");
//    }
//}
