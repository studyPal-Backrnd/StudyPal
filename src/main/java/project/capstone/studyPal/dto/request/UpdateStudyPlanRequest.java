package project.capstone.studyPal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateStudyPlanRequest {
    private Long studyPlanId;
    private String title;
    private String description;
    private Set<CreateScheduleRequest> createScheduleRequests;
    private LocalDate startDate;
    private LocalDate endDate;
}
