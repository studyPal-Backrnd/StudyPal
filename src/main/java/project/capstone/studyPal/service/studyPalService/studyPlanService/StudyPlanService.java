package project.capstone.studyPal.service.studyPalService.studyPlanService;

import project.capstone.studyPal.data.models.StudyPlan;
import project.capstone.studyPal.dto.request.CreateStudyPlanRequest;
import project.capstone.studyPal.dto.request.UpdateStudyPlanRequest;
import project.capstone.studyPal.exception.DateTimeException;
import project.capstone.studyPal.exception.NotFoundException;

import java.util.List;

public interface StudyPlanService {
    String createStudyPlan(CreateStudyPlanRequest createStudyPlanRequest) throws DateTimeException;
    StudyPlan getStudyPlanById(Long studyPlanId)throws NotFoundException;
    String updateStudyPlan(UpdateStudyPlanRequest updateStudyPlanRequest)throws NotFoundException, DateTimeException;
    void deleteStudyPlan(Long studyPlanId);
    List<StudyPlan> getAllStudyPlans();
}
