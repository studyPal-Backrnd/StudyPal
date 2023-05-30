package project.capstone.studyPal.service.studyPalService.studyPlanService;

import org.apache.http.impl.cookie.DateParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.dto.request.CreateScheduleRequest;
import project.capstone.studyPal.dto.request.CreateStudyPlanRequest;
import project.capstone.studyPal.dto.request.UpdateStudyPlanRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class StudyPlanServiceImplTest {
    @Autowired StudyPlanService studyPlanService;
    private CreateStudyPlanRequest createStudyPlanRequest1;
    private CreateStudyPlanRequest createStudyPlanRequest2;
    private UpdateStudyPlanRequest updateStudyPlanRequest;

    @BeforeEach
    void setUp() {
        createStudyPlanRequest1 = new CreateStudyPlanRequest();
        createStudyPlanRequest1.setUserId(1L);
        createStudyPlanRequest1.setTitle("Read java");
        createStudyPlanRequest1.setDescription("I want to read Java from chapter 1 to 5");

        CreateScheduleRequest createScheduleRequest1 = new CreateScheduleRequest();
        createScheduleRequest1.setPurpose("Java chapter 1");
        createScheduleRequest1.setStartTime(LocalTime.parse("01:50 pm", DateTimeFormatter.ofPattern("hh:mm a")));
        createScheduleRequest1.setEndTime(LocalTime.parse("04:50 pm", DateTimeFormatter.ofPattern("hh:mm a")));
        createScheduleRequest1.setStartDate(LocalDate.now().plusDays(1));
        createScheduleRequest1.setEndDate(LocalDate.now().plusDays(1));

        CreateScheduleRequest createScheduleRequest2 = new CreateScheduleRequest();
        createScheduleRequest2.setPurpose("Java chapter 2");
        createScheduleRequest2.setStartTime(LocalTime.now().plusHours(2));
        createScheduleRequest2.setEndTime(LocalTime.now().plusHours(3));
        createScheduleRequest2.setStartDate(LocalDate.now().plusDays(1));
        createScheduleRequest2.setEndDate(LocalDate.now().plusDays(1));


        createStudyPlanRequest1.setScheduleRequests(Set.of(createScheduleRequest1, createScheduleRequest2));
        createStudyPlanRequest1.setStartDate(LocalDate.now().plusDays(2));
        createStudyPlanRequest1.setEndDate(LocalDate.now().plusDays(3));
    }

    @Test
    void createStudyPlan() {
        String response = studyPlanService.createStudyPlan(createStudyPlanRequest1);
        assertThat(response).isEqualTo("Study plan created");
    }

    @Test
    void getStudyPlanById() {
    }

    @Test
    void updateStudyPlan() {
    }

    @Test
    void deleteStudyPlan() {
    }
}