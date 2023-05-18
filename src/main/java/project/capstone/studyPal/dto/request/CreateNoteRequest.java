package project.capstone.studyPal.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateNoteRequest {
    private Long userId;
    @NotBlank(message = "Note title cannot be blank")
    private String title;
    @NotBlank(message = "Note body cannot be blank")
    private String body;
}
