package project.capstone.studyPal.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.studyPal.data.models.StudyPlan;
import project.capstone.studyPal.dto.request.CreateStudyPlanRequest;
import project.capstone.studyPal.dto.request.UpdateStudyPlanRequest;
import project.capstone.studyPal.exception.DateTimeException;
import project.capstone.studyPal.service.studyPalService.studyPlanService.StudyPlanService;
@AllArgsConstructor
@RequestMapping("api/v1/studypal/studyplan")
@RestController
public class StudyPlanController {

    private final StudyPlanService studyPlanService;

    @GetMapping("")
    public ResponseEntity<?> getAllStudyPlan() {
        return new ResponseEntity<>(studyPlanService.getAllStudyPlans(), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<String> createStudyPlan(@Valid @RequestBody CreateStudyPlanRequest request) throws DateTimeException {
        return new ResponseEntity<>(studyPlanService.createStudyPlan(request), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudyPlan> getStudyPlanById(@Valid @PathVariable long id) {
        return new ResponseEntity<>(studyPlanService.getStudyPlanById(id), HttpStatus.OK);
    }


    @PutMapping("update")
    public ResponseEntity<String> updateStudyPlan(@Valid @RequestBody UpdateStudyPlanRequest request) throws DateTimeException {
        return new ResponseEntity<>(studyPlanService.updateStudyPlan(request), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public void deleteStudyPlanById(@Valid @PathVariable long id){
        studyPlanService.deleteStudyPlan(id);
    }


}