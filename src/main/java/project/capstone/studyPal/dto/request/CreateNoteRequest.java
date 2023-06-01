package project.capstone.studyPal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotNull(message = "Note title cannot be null")
    @NotEmpty(message = "Note title cannot be empty")
    @Pattern(message = "Note title can only be letters", regexp = "^[a-zA-Z]+$")
    private String title;
    @NotBlank(message = "Note body cannot be blank")
    @NotNull(message = "Note body cannot be null")
    @NotEmpty(message = "Note body cannot be empty")
    private String body;
}
