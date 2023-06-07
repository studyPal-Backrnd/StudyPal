package project.capstone.studyPal.service.studyPalService.studyPlanService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.data.models.StudyPlan;
import project.capstone.studyPal.dto.request.CreateScheduleRequest;
import project.capstone.studyPal.dto.request.CreateStudyPlanRequest;
import project.capstone.studyPal.dto.request.UpdateStudyPlanRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        createStudyPlanRequest1.setTitle("Creating my first study plan");
        createStudyPlanRequest1.setDescription("I am going to read Java from chapter 1 to chapter 5");

        CreateScheduleRequest createScheduleRequest = new CreateScheduleRequest();
        createScheduleRequest.setPurpose("Chapter 1");
        createScheduleRequest.setStartDateTime(LocalDateTime.now().plusMinutes(10L));
        createScheduleRequest.setEndDateTime(LocalDateTime.now().plusMinutes(50L));
        createStudyPlanRequest1.setScheduleRequests(Set.of(createScheduleRequest));

        CreateScheduleRequest createScheduleRequest1 = new CreateScheduleRequest();
        createScheduleRequest1.setPurpose("Chapter 2");
        createScheduleRequest1.setStartDateTime(LocalDateTime.now().plusMinutes(20L));
        createScheduleRequest1.setEndDateTime(LocalDateTime.now().plusMinutes(70L));


        updateStudyPlanRequest = new UpdateStudyPlanRequest();
        updateStudyPlanRequest.setStudyPlanId(1L);
        updateStudyPlanRequest.setTitle("Updating my first study plan");
        updateStudyPlanRequest.setDescription("I am now going to read Python 101 from chapter 1 to 5");

        CreateScheduleRequest createScheduleRequest2 = new CreateScheduleRequest();
        createScheduleRequest2.setPurpose("Chapter 2");
        createScheduleRequest2.setStartDateTime(LocalDateTime.now().plusMinutes(30L));
        createScheduleRequest2.setEndDateTime(LocalDateTime.now().plusMinutes(90L));

        updateStudyPlanRequest.setCreateScheduleRequests(Set.of(createScheduleRequest2));
        updateStudyPlanRequest.setStartDate(LocalDate.now().plusDays(2L));
        updateStudyPlanRequest.setEndDate(LocalDate.now().plusDays(4L));
    }

    @Test
    void createStudyPlan() {
        String response = studyPlanService.createStudyPlan(createStudyPlanRequest1);
        assertThat(response).isEqualTo("Study plan created");
    }

    @Test
    void getStudyPlanById() {
//        StudyPlan foundStudyPlan = studyPlanService.getStudyPlanById(1L);
//        assertThat(foundStudyPlan.getTitle()).isEqualTo(createStudyPlanRequest1.getTitle());
//        assertThat(foundStudyPlan.getDescription()).isEqualTo(createStudyPlanRequest1.getDescription());
    }

    @Test
    void updateStudyPlan() {
        String response = studyPlanService.updateStudyPlan(updateStudyPlanRequest);
        assertThat(response).isEqualTo("Study plan updated");
    }

    @Test
    void studyPlanCount(){
        Long count = studyPlanService.studyPlanCount();
        assertThat(count).isEqualTo(1L);
    }
    @Test
    void getAllStudyPlans(){
        assertThat(studyPlanService.studyPlanCount()).isEqualTo(1L);
    }

    @Test
    void deleteStudyPlan() {
//        String response = studyPlanService.deleteStudyPlan(1L);
//        assertThat(response).isEqualTo("Study plan deleted");
//        assertThat(studyPlanService.studyPlanCount()).isEqualTo(0L);
    }
}