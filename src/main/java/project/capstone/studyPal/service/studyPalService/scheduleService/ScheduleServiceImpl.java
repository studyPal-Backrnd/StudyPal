package project.capstone.studyPal.service.studyPalService.scheduleService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.data.models.Schedule;
import project.capstone.studyPal.data.repository.ScheduleRepository;
import project.capstone.studyPal.dto.request.CreateScheduleRequest;
import project.capstone.studyPal.exception.DateTimeException;
import project.capstone.studyPal.exception.LogicException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    @Override
    public Schedule createASchedule(CreateScheduleRequest createScheduleRequest) throws DateTimeException {
        Schedule schedule = new Schedule();
        validateDate(createScheduleRequest.getStartDate());
        validateDate(createScheduleRequest.getEndDate());
        validateTime(createScheduleRequest.getStartTime());
        validateTime(createScheduleRequest.getEndTime());
        schedule.setPurpose(createScheduleRequest.getPurpose());
        schedule.setStartDate(createScheduleRequest.getStartDate());
        schedule.setEndDate(createScheduleRequest.getEndDate());
        schedule.setStartTime(createScheduleRequest.getStartTime());
        schedule.setEndTime(createScheduleRequest.getEndTime());
        return schedule;
    }


    @Override
    public Set<Schedule> saveAllSchedules(Collection<Schedule> schedules) {
        return new HashSet<>(scheduleRepository.saveAll(schedules));
    }

    @Override
    public Schedule getScheduleById(Long scheduleId) throws LogicException {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new LogicException("Schedule not found or schedule is deleted"));
    }

    @Override
    public void deleteScheduleById(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    private static void validateDate(LocalDate date) throws DateTimeException {
        if (date.isBefore(LocalDate.now()))
            throw new DateTimeException("Invalid date. Date cannot be the past");
    }
    private static void validateTime(LocalTime localTime) throws DateTimeException {
        if (localTime.isBefore(LocalTime.now()))
            throw new DateTimeException("Invalid time. Time cannot be the past");
    }
}
