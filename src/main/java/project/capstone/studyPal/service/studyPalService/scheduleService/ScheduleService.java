package project.capstone.studyPal.service.studyPalService.scheduleService;

import project.capstone.studyPal.data.models.Schedule;
import project.capstone.studyPal.dto.request.CreateScheduleRequest;
import project.capstone.studyPal.exception.DateTimeException;
import project.capstone.studyPal.exception.LogicException;

import java.util.Collection;
import java.util.Set;

public interface ScheduleService {
    Schedule createASchedule(CreateScheduleRequest createScheduleRequest) throws DateTimeException;
    Set<Schedule> saveAllSchedules(Collection<Schedule> schedules);
    Schedule getScheduleById(Long scheduleId) throws LogicException;
    void deleteScheduleById(Long scheduleId);
}
