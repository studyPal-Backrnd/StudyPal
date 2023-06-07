package project.capstone.studyPal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.capstone.studyPal.data.models.Schedule;

import java.time.LocalDate;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateStudyPlanRequest {
    private Long userId;

    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    @NotBlank(message = "Cannot be blank")
    private String title;

    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    @NotBlank(message = "Cannot be blank")
    private String description;

    private Set<CreateScheduleRequest> scheduleRequests;
}
