package project.capstone.studyPal.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.studyPal.data.models.StudyPlan;
import project.capstone.studyPal.dto.request.CreateStudyPlanRequest;
import project.capstone.studyPal.dto.request.GetStudyPlanRequest;
import project.capstone.studyPal.dto.request.UpdateStudyPlanRequest;
import project.capstone.studyPal.exception.DateTimeException;
import project.capstone.studyPal.service.studyPalService.studyPlanService.StudyPlanService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/studypal/studyplan")
@RestController
@CrossOrigin(origins = "*")
public class StudyPlanController {

    private final StudyPlanService studyPlanService;

    @PostMapping("create")
    public ResponseEntity<?> createStudyPlan(@Valid @RequestBody CreateStudyPlanRequest request){
        String response = studyPlanService.createStudyPlan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("get_study_plan")
    public ResponseEntity<?> getStudyPlanById(@Valid @RequestBody GetStudyPlanRequest request) {
        StudyPlan studyPlan = studyPlanService.getStudyPlanById(request);
        return ResponseEntity.status(HttpStatus.OK).body(studyPlan);
    }
    @GetMapping("all/get/{user_id}")
    public ResponseEntity<?> getAllStudyPlans(@Valid @PathVariable Long user_id){
        List<StudyPlan> studyPlans = studyPlanService.getAllStudyPlans(user_id);
        return ResponseEntity.ok(studyPlans);
    }
    @PutMapping("update")
    public ResponseEntity<?> updateStudyPlan(@Valid @RequestBody UpdateStudyPlanRequest request) {
        String response = studyPlanService.updateStudyPlan(request);
         return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteStudyPlanById(@Valid @RequestBody GetStudyPlanRequest request){
        studyPlanService.deleteStudyPlan(request);
        return ResponseEntity.ok("Study plan deleted");
    }
    @DeleteMapping("all/delete/{user_id}")
    public ResponseEntity<?>deleteAllStudyPlans(@Valid @PathVariable Long user_id){
        studyPlanService.deleteAllStudyPlans(user_id);
        return ResponseEntity.ok("All Study plans deleted");
    }
}