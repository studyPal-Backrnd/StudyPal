package project.capstone.studyPal.service.sendiblueEmailService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class SendiblueMailServiceImplTest {
    @Autowired SendiblueMailService sendiblueMailService;
    private EmailNotificationRequest emailNotificationRequest;

    @BeforeEach
    void setUp() {
        emailNotificationRequest = new EmailNotificationRequest();
        emailNotificationRequest.setTo(List.of(new Recipient("temx", "osodavid001@gmail.com")));
        emailNotificationRequest.setHtmlContent("Your verification token is: 12345");
    }

    @Test
    void sendHtmlMail() {
        String response = sendiblueMailService.sendHtmlMail(emailNotificationRequest);
        assertThat(response).isNotNull();
    }
}