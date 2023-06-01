package project.capstone.studyPal.service.studyPalService.scheduleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.data.models.Schedule;
import project.capstone.studyPal.dto.request.CreateScheduleRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ScheduleServiceImplTest {
    @Autowired ScheduleService scheduleService;
    private CreateScheduleRequest createScheduleRequest1;
    private CreateScheduleRequest createScheduleRequest2;

    @BeforeEach
    void setUp() {
        createScheduleRequest1 = new CreateScheduleRequest();
        createScheduleRequest1.setPurpose("Creating my first schedule");
        createScheduleRequest1.setStartDateTime(LocalDateTime.now().plusMinutes(10L));
        createScheduleRequest1.setEndDateTime(LocalDateTime.now().plusMinutes(50L));

        createScheduleRequest2 = new CreateScheduleRequest();
        createScheduleRequest2.setPurpose("\nCreating my second schedule");
        createScheduleRequest2.setStartDateTime(LocalDateTime.now().plusMinutes(30L));
        createScheduleRequest2.setEndDateTime(LocalDateTime.now().plusHours(70L));

    }

    @Test
    void createASchedule() {
        Schedule createdSchedule = scheduleService.createASchedule(createScheduleRequest1);
        System.out.println(createdSchedule);
        System.out.println(createdSchedule.getPurpose());
        System.out.println(createdSchedule.getStartDateTime());
        System.out.println(createdSchedule.getEndDateTime());
        Schedule createdSchedule2 = scheduleService.createASchedule(createScheduleRequest2);
        System.out.println(createdSchedule2.getPurpose());
        System.out.println(createdSchedule2.getStartDateTime());
        System.out.println(createdSchedule2.getEndDateTime());

    }

    @Test
    void getScheduleById() {
    }

    @Test
    void getAllSchedules() {
    }

    @Test
    void deleteScheduleById() {
    }
}