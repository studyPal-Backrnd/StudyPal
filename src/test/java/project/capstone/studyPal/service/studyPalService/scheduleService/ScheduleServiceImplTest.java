package project.capstone.studyPal.service.studyPalService.scheduleService;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.data.models.Schedule;
import project.capstone.studyPal.dto.request.CreateScheduleRequest;

import java.time.LocalTime;

@SpringBootTest
@RequiredArgsConstructor
class ScheduleServiceImplTest {
    private CreateScheduleRequest createScheduleRequest;
    @Autowired  private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        createScheduleRequest = new CreateScheduleRequest();
        createScheduleRequest.setPurpose("read Dietel dietel chapter 1");
        createScheduleRequest.setStartDate("20/05/2023");
        createScheduleRequest.setEndDate("20/05/2023");
        createScheduleRequest.setStartTime(LocalTime.now().plusHours(1));
        createScheduleRequest.setEndTime(LocalTime.now().plusHours(1).plusMinutes(3));
    }

    @Test
    void createASchedule() {
        try{
        Schedule createSchedule = scheduleService.createASchedule(createScheduleRequest);
            System.out.println(createSchedule);
        }catch (Exception exception){
            System.out.println("error");
        }
    }

    @Test
    void getScheduleById() {
    }

    @Test
    void updateSchedule() {
    }

    @Test
    void deleteScheduleById() {
    }
}