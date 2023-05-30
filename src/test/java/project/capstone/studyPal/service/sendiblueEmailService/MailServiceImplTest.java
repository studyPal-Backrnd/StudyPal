package project.capstone.studyPal.service.sendiblueEmailService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.dto.request.EmailNotificationRequest;
import project.capstone.studyPal.dto.request.Recipient;
import project.capstone.studyPal.service.MailService.MailService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class MailServiceImplTest {
    @Autowired
    MailService mailService;
    private EmailNotificationRequest emailNotificationRequest;

    @BeforeEach
    void setUp() {
        emailNotificationRequest = new EmailNotificationRequest();
        emailNotificationRequest.setTo(List.of(new Recipient("temx", "osodavid001@gmail.com")));
        emailNotificationRequest.setSubject("Verification ");
        emailNotificationRequest.setHtmlContent("Your verification token is: 12345");
    }

    @Test
    void sendHtmlMail() {
        String response = mailService.sendHtmlMail(emailNotificationRequest);
        assertThat(response).isNotNull();
    }
}