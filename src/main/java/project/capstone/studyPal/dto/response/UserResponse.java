package project.capstone.studyPal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.data.models.Shelf;
import project.capstone.studyPal.data.models.StudyPlan;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private List<Note> notes;
    private Shelf shelf;
    private boolean isEnabled;
    private List<StudyPlan> studyPlans;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
