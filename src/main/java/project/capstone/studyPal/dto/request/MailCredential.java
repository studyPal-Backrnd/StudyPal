package project.capstone.studyPal.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.capstone.studyPal.config.app.AppConfig;
import project.capstone.studyPal.config.app.MailConfig;

import java.time.LocalDateTime;

@Getter
@Setter
public class MailCredential {


    private String recipientEmail;
    private String token;
    private String subject;
    private String text;
    private final LocalDateTime expiryTime = LocalDateTime.now().plusSeconds(120L);


}
