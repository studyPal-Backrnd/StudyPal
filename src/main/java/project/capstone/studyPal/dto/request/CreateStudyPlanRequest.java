package project.capstone.studyPal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.capstone.studyPal.data.models.Schedule;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateStudyPlanRequest {
    private String title;
    private String description;
    private Set<CreateScheduleRequest> scheduleRequests;
    private String startDate;
    private String endDate;
}
