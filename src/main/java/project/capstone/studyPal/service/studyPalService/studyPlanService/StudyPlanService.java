package project.capstone.studyPal.service.studyPalService.studyPlanService;

import project.capstone.studyPal.data.models.StudyPlan;
import project.capstone.studyPal.dto.request.CreateStudyPlanRequest;
import project.capstone.studyPal.dto.request.GetStudyPlanRequest;
import project.capstone.studyPal.dto.request.UpdateStudyPlanRequest;
import project.capstone.studyPal.exception.DateTimeException;
import project.capstone.studyPal.exception.NotFoundException;

import java.util.List;

public interface StudyPlanService {
    String createStudyPlan(CreateStudyPlanRequest createStudyPlanRequest);
    StudyPlan getStudyPlanById(GetStudyPlanRequest request);
    String updateStudyPlan(UpdateStudyPlanRequest updateStudyPlanRequest);
    List<StudyPlan> getAllStudyPlans(Long userId);
    Long studyPlanCount();
    void deleteStudyPlan(GetStudyPlanRequest request);
    void deleteAllStudyPlans(Long userId);
}
