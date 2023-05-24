package project.capstone.studyPal.service.studyPalService.scheduleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.data.models.Schedule;
import project.capstone.studyPal.dto.request.CreateScheduleRequest;
import project.capstone.studyPal.exception.DateTimeException;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class ScheduleServiceImplTest {
    @Autowired private ScheduleService scheduleService;
    private CreateScheduleRequest createScheduleRequest;

    @BeforeEach
    void setUp() {
        createScheduleRequest = new CreateScheduleRequest();
        createScheduleRequest.setPurpose("Read java 101");
        createScheduleRequest.setStartDate(LocalDate.now());
        createScheduleRequest.setEndDate(LocalDate.now().plusDays(1));
        createScheduleRequest.setStartTime(LocalTime.now().plusHours(2L));
        createScheduleRequest.setEndTime(LocalTime.now().plusHours(3L));
    }

    @Test
    void createASchedule() throws DateTimeException {
        try{
        Schedule response = scheduleService.createASchedule(createScheduleRequest);
        System.out.println(response);
        assertThat(response).isNotNull();
        }catch (DateTimeException dateTimeException){
            System.out.println(dateTimeException.getMessage());
        }
    }

//    You must at least a study plan before you can create or get a schedule
    @Test
    void getScheduleById() {
    }

    @Test
    void deleteScheduleById() {
    }
}