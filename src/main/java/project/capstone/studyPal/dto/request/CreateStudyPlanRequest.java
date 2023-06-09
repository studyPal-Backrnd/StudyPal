package project.capstone.studyPal.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@JsonIgnoreProperties(ignoreUnknown = true)
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
