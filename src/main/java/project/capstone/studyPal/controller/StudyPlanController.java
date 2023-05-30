package project.capstone.studyPal.controller;

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
@RequestMapping("studyPal/api/")
@RestController
public class StudyPlanController {

    private final StudyPlanService studyPlanService;

    @PostMapping("createStudyPlan")
    public ResponseEntity<String> createStudyPlan(@RequestBody CreateStudyPlanRequest request) throws DateTimeException {
        return new ResponseEntity(studyPlanService.createStudyPlan(request), HttpStatus.CREATED);
    }

    @GetMapping("studyPal/GET_STUDY_PLAN")
    public ResponseEntity<StudyPlan> getStudyPlanById(@PathVariable long id) {
        return new ResponseEntity<>(studyPlanService.getStudyPlanById(id), HttpStatus.OK);
    }


    @PutMapping("studyPal/UPDATE_STUDY_PLAN")
    public ResponseEntity<String> updateStudyPlan(@RequestBody UpdateStudyPlanRequest request) throws DateTimeException {
        return new ResponseEntity<>(studyPlanService.updateStudyPlan(request), HttpStatus.OK);
    }
    @DeleteMapping("studyPal/DELETE_STUDY_PLAN")
    public void deleteStudyPlanById(long id){
        studyPlanService.deleteStudyPlan(id);
    }


}