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
        LocalDate startDate = parseDate(createScheduleRequest.getStartDate());
        validateDate(startDate);
        LocalDate endDate = parseDate(createScheduleRequest.getEndDate());
        validateDate(endDate);
        if (createScheduleRequest.getStartTime().isBefore(LocalTime.now()))
            throw new DateTimeException("Invalid time. Time cannot be the past");
        if (createScheduleRequest.getEndTime().isBefore(createScheduleRequest.getStartTime()))
            throw new DateTimeException("Invalid time. End time cannot be the past");
        schedule.setPurpose(createScheduleRequest.getPurpose());
        schedule.setStartDate(startDate);
        schedule.setEndDate(endDate);
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
    private LocalDate parseDate(String startDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(startDate, dateFormatter);
    }
    private static void validateDate(LocalDate date) throws DateTimeException {
        if (date.isBefore(LocalDate.now()))
            throw new DateTimeException("Invalid date. Date cannot be the past");
    }
}
