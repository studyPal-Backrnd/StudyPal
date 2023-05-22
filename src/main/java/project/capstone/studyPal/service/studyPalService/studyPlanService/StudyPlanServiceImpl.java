package project.capstone.studyPal.service.studyPalService.studyPlanService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.data.models.Schedule;
import project.capstone.studyPal.data.models.StudyPlan;
import project.capstone.studyPal.data.repository.StudyPlanRepository;
import project.capstone.studyPal.dto.request.CreateScheduleRequest;
import project.capstone.studyPal.dto.request.CreateStudyPlanRequest;
import project.capstone.studyPal.dto.request.UpdateStudyPlanRequest;
import project.capstone.studyPal.exception.DateTimeException;
import project.capstone.studyPal.exception.NotFoundException;
import project.capstone.studyPal.service.studyPalService.scheduleService.ScheduleService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudyPlanServiceImpl implements StudyPlanService{
    private final StudyPlanRepository studyPlanRepository;
    private final ScheduleService scheduleService;
    @Override
    public String createStudyPlan(CreateStudyPlanRequest createStudyPlanRequest) throws DateTimeException {
        StudyPlan studyPlan = new StudyPlan();
        Set<Schedule> newSchedules = new HashSet<>();
        studyPlan.setTitle(createStudyPlanRequest.getTitle());
        studyPlan.setDescription(createStudyPlanRequest.getDescription());
        for(CreateScheduleRequest newSchedule : createStudyPlanRequest.getScheduleRequests()){
            Schedule createdSchedule = scheduleService.createASchedule(newSchedule);
            newSchedules.add(createdSchedule);
        }
        Set<Schedule> savedSchedules = scheduleService.saveAllSchedules(newSchedules);
        studyPlan.setSchedules(savedSchedules);
        studyPlan.setCreatedDate(parseDate(createStudyPlanRequest.getStartDate()));
        studyPlan.setEndDate(parseDate(createStudyPlanRequest.getEndDate()));
        studyPlanRepository.save(studyPlan);
        return "Study plan created";
    }

    @Override
    public StudyPlan getStudyPlanById(Long studyPlanId) {
        return studyPlanRepository.findById(studyPlanId).orElseThrow(
                ()-> new NotFoundException("Study plan not found or study deleted"));
    }

    @Override
    public String updateStudyPlan(UpdateStudyPlanRequest updateStudyPlanRequest) throws DateTimeException {
        StudyPlan foundStudyPlan = getStudyPlanById(updateStudyPlanRequest.getStudyPlanId());
        Set<Schedule> updatedSchedules = new HashSet<>();
        foundStudyPlan.setTitle(updateStudyPlanRequest.getTitle());
        foundStudyPlan.setDescription(updateStudyPlanRequest.getDescription());
        foundStudyPlan.setCreatedDate(parseDate(updateStudyPlanRequest.getStartDate()));
        foundStudyPlan.setEndDate(parseDate(updateStudyPlanRequest.getEndDate()));
        for(CreateScheduleRequest updateSchedule : updateStudyPlanRequest.getCreateScheduleRequests()){
            Schedule createdSchedule = scheduleService.createASchedule(updateSchedule);
            updatedSchedules.add(createdSchedule);
        }
        Set<Schedule> savedSchedules = scheduleService.saveAllSchedules(updatedSchedules);
        foundStudyPlan.setSchedules(savedSchedules);
        foundStudyPlan.setCreatedDate(parseDate(updateStudyPlanRequest.getStartDate()));
        foundStudyPlan.setEndDate(parseDate(updateStudyPlanRequest.getEndDate()));
        studyPlanRepository.save(foundStudyPlan);
        return "Study plan updated";
    }

    @Override
    public void deleteStudyPlan(Long studyPlanId) {
        studyPlanRepository.deleteById(studyPlanId);
    }
    private LocalDate parseDate(String date) throws DateTimeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
        LocalDate parseDate = LocalDate.parse(date, dateFormatter);
        if(parseDate.isBefore(LocalDate.now()))
            throw new DateTimeException("Invalid date. Date cannot be the past");
        else return parseDate;
    }
}
