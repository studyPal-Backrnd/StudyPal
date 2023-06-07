package project.capstone.studyPal.service.studyPalService.studyPlanService;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.data.models.Schedule;
import project.capstone.studyPal.data.models.StudyPlan;
import project.capstone.studyPal.data.repository.StudyPlanRepository;
import project.capstone.studyPal.dto.request.CreateScheduleRequest;
import project.capstone.studyPal.dto.request.CreateStudyPlanRequest;
import project.capstone.studyPal.dto.request.GetStudyPlanRequest;
import project.capstone.studyPal.dto.request.UpdateStudyPlanRequest;
import project.capstone.studyPal.exception.DateTimeException;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.exception.NotFoundException;
import project.capstone.studyPal.service.studyPalService.scheduleService.ScheduleService;
import project.capstone.studyPal.service.studyPalService.userService.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudyPlanServiceImpl implements StudyPlanService {
    private final StudyPlanRepository studyPlanRepository;
    private final ScheduleService scheduleService;
    private final UserService userService;

    @Override
    public String createStudyPlan(@NotNull CreateStudyPlanRequest createStudyPlanRequest){
        AppUser foundUser = userService.getUserById(createStudyPlanRequest.getUserId());
        StudyPlan studyPlan = new StudyPlan();
        studyPlan.setTitle(createStudyPlanRequest.getTitle());
        studyPlan.setDescription(createStudyPlanRequest.getDescription());
        Set<Schedule> savedSchedules = getCreatedSchedules(createStudyPlanRequest.getScheduleRequests());
        studyPlan.setSchedules(savedSchedules);
        foundUser.getStudyPlans().add(studyPlan);
        userService.updateUser(foundUser);
        return "Study plan created";
    }

    @Override
    public StudyPlan getStudyPlanById(GetStudyPlanRequest request) {
        AppUser user = userService.getUserById(request.getUserId());
        List<StudyPlan> studyPlans = user.getStudyPlans();
        for(StudyPlan studyPlan : studyPlans){
            if(studyPlan.getStudyPlanId().equals(request.getStudyPlanId()))
                return studyPlan;
        }
        throw new RuntimeException("Study plan not found");
//        return studyPlanRepository.findById(studyPlanId).orElseThrow(
//                () -> new NotFoundException("Study plan not found or study deleted"));
    }

    @Override
    public String updateStudyPlan(UpdateStudyPlanRequest updateStudyPlanRequest){
        AppUser foundUser = userService.getUserById(updateStudyPlanRequest.getUserId());
        List<StudyPlan> studyPlans = foundUser.getStudyPlans();
        Set<Schedule> savedSchedules = getCreatedSchedules(updateStudyPlanRequest.getCreateScheduleRequests());
        for(StudyPlan studyPlan : studyPlans){
            if(studyPlan.getStudyPlanId().equals(updateStudyPlanRequest.getStudyPlanId())){
                studyPlan.setTitle(updateStudyPlanRequest.getTitle());
                studyPlan.setDescription(updateStudyPlanRequest.getDescription());
                studyPlan.setSchedules(savedSchedules);
                studyPlan.setUpdatedAt(LocalDateTime.now());
                userService.updateUser(foundUser);
                return "Study plan updated";
            }
        }
        throw new RuntimeException("Error update study plan");
    }
    @Override
    public List<StudyPlan> getAllStudyPlans(Long userId) {
        AppUser foundUser = userService.getUserById(userId);
        return foundUser.getStudyPlans();
//        return studyPlanRepository.findAll();
    }

    @Override
    public void deleteStudyPlan(GetStudyPlanRequest request) {
        AppUser foundUser = userService.getUserById(request.getUserId());
        List<StudyPlan> studyPlans = foundUser.getStudyPlans();
        studyPlans.removeIf(studyPlan -> studyPlan.getStudyPlanId().equals(request.getStudyPlanId()));
        userService.updateUser(foundUser);
//        studyPlanRepository.deleteById(studyPlanId);
//        return "Study plan deleted";
    }

    @Override
    public void deleteAllStudyPlans(Long userId) {
        AppUser foundUser = userService.getUserById(userId);
        foundUser.getStudyPlans().clear();
        userService.updateUser(foundUser);
    }


    @Override
    public Long studyPlanCount() {
        return studyPlanRepository.count();
    }

    private Set<Schedule> getCreatedSchedules(@NotNull Set<CreateScheduleRequest> createScheduleRequests) {
        Set<Schedule> newSchedules = new HashSet<>();
        for (CreateScheduleRequest newSchedule : createScheduleRequests) {
            Schedule createdSchedule = scheduleService.createASchedule(newSchedule);
            newSchedules.add(createdSchedule);
        }
        return scheduleService.saveAllSchedules(newSchedules);
    }
}