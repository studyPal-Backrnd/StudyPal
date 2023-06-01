package project.capstone.studyPal.service.studyPalService.scheduleService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.data.models.Schedule;
import project.capstone.studyPal.data.repository.ScheduleRepository;
import project.capstone.studyPal.dto.request.CreateScheduleRequest;
import project.capstone.studyPal.exception.DateTimeException;
import project.capstone.studyPal.exception.LogicException;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule createASchedule(CreateScheduleRequest createScheduleRequest) throws DateTimeException {
        validateDateTime(createScheduleRequest.getStartDateTime());
        validateDateTime(createScheduleRequest.getEndDateTime());
        Schedule schedule = new Schedule();
        schedule.setPurpose(createScheduleRequest.getPurpose());
        schedule.setStartDateTime(createScheduleRequest.getStartDateTime());
        schedule.setEndDateTime(createScheduleRequest.getEndDateTime());
        return schedule;
    }

    private void validateDateTime(LocalDateTime dateTime) {
        if(dateTime.isBefore(LocalDateTime.now()))
            throw new DateTimeException("Date time cannot be in the past");
    }


    @Override
    public Set<Schedule> saveAllSchedules(Collection<Schedule> schedules) {
        return new HashSet<>(scheduleRepository.saveAll(schedules));
    }

    @Override
    public Schedule getScheduleById(Long scheduleId) throws LogicException {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new LogicException("Schedule not found or schedule is deleted"));
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public void deleteScheduleById(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}
