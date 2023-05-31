package project.capstone.studyPal.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateScheduleRequest {

    @Pattern(message = "The purpose can only be letters with minimal numbers", regexp = "^(?:[a-zA-Z]*[0-9]){0,3}[a-zA-Z]+[a-zA-Z0-9]*$")
    private String purpose;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
