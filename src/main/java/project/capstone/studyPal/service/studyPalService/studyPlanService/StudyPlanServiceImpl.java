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
import project.capstone.studyPal.dto.request.UpdateStudyPlanRequest;
import project.capstone.studyPal.exception.DateTimeException;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.exception.NotFoundException;
import project.capstone.studyPal.service.studyPalService.scheduleService.ScheduleService;
import project.capstone.studyPal.service.studyPalService.userService.UserService;

import java.time.LocalDate;
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
    public String createStudyPlan(@NotNull CreateStudyPlanRequest createStudyPlanRequest) throws DateTimeException {
        AppUser foundUser = userService.getUserById(createStudyPlanRequest.getUserId());
        if (!(foundUser.isEnabled()))
            throw new LogicException("User is not enabled");
        else {
            validateStudyPlanDate(createStudyPlanRequest.getStartDate());
            validateStudyPlanDate(createStudyPlanRequest.getEndDate());
            StudyPlan studyPlan = new StudyPlan();
            studyPlan.setTitle(createStudyPlanRequest.getTitle());
            studyPlan.setDescription(createStudyPlanRequest.getDescription());
            Set<Schedule> savedSchedules = getCreatedSchedules(createStudyPlanRequest.getScheduleRequests());
            studyPlan.setSchedules(savedSchedules);
            studyPlan.setCreatedDate(createStudyPlanRequest.getStartDate());
            studyPlan.setEndDate(createStudyPlanRequest.getEndDate());
            foundUser.getStudyPlans().add(studyPlan);
            userService.updateUser(foundUser);
            return "Study plan created";
        }
    }

    private void validateStudyPlanDate(LocalDate date) {
        if (date.isBefore(LocalDate.now()))
            throw new DateTimeException("Date cannot be in the past");
    }

    @Override
    public StudyPlan getStudyPlanById(Long studyPlanId) {
        return studyPlanRepository.findById(studyPlanId).orElseThrow(
                () -> new NotFoundException("Study plan not found or study deleted"));
    }

    @Override
    public String updateStudyPlan(@NotNull UpdateStudyPlanRequest updateStudyPlanRequest) throws DateTimeException {
        StudyPlan foundStudyPlan = getStudyPlanById(updateStudyPlanRequest.getStudyPlanId());
        foundStudyPlan.setTitle(updateStudyPlanRequest.getTitle());
        foundStudyPlan.setDescription(updateStudyPlanRequest.getDescription());
        foundStudyPlan.setCreatedDate(updateStudyPlanRequest.getStartDate());
        foundStudyPlan.setEndDate(updateStudyPlanRequest.getEndDate());
        Set<Schedule> savedSchedules = getCreatedSchedules(updateStudyPlanRequest.getCreateScheduleRequests());
        foundStudyPlan.setSchedules(savedSchedules);
        foundStudyPlan.setCreatedDate(updateStudyPlanRequest.getStartDate());
        foundStudyPlan.setEndDate(updateStudyPlanRequest.getEndDate());
        studyPlanRepository.save(foundStudyPlan);
        return "Study plan updated";
    }

    @Override
    public String deleteStudyPlan(Long studyPlanId) {
        studyPlanRepository.deleteById(studyPlanId);
        return "Study plan deleted";
    }

    @Override
    public List<StudyPlan> getAllStudyPlans() {
        return studyPlanRepository.findAll();
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