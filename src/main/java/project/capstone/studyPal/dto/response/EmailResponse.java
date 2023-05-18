package project.capstone.studyPal.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmailResponse {
    private Long id;
    private String message;
    private boolean success;
}
