package project.capstone.studyPal.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Sender {
    private String name;
    private String email;
}
