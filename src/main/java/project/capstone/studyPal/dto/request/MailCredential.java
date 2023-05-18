package project.capstone.studyPal.data.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MailCredential {
    private String email;
    private String token;
    private LocalDateTime expiryTime = LocalDateTime.now().plusSeconds(120L);

}
