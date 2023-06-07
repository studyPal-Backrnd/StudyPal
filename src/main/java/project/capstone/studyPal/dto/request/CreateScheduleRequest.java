package project.capstone.studyPal.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateScheduleRequest {

    private String purpose;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}
