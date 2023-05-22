package project.capstone.studyPal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateScheduleRequest {
    private String purpose;
    private String startDate;
    private String endDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
