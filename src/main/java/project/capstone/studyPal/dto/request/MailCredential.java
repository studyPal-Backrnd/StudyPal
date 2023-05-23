package project.capstone.studyPal.dto.request;

import lombok.*;
import project.capstone.studyPal.service.sendiblueEmailService.EmailNotificationRequest;

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
