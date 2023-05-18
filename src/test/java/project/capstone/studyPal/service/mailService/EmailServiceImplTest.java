package project.capstone.studyPal.service.mailService;

import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.request.Recipient;
import project.capstone.studyPal.dto.request.SendMailRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmailServiceImplTest {
    @Autowired
    private EmailService emailService;
    private SendMailRequest mailRequest;

    @BeforeEach
    void setUp() {
        List<Recipient> to = List.of(
                new Recipient("study pal", "")
        );

        mailRequest = new SendMailRequest(to, "test sending mail", "Let check if it working");
    }

    @Test
    void sendMail() {
//        var response = emailService.sendMail(mailRequest);
//        assertThat(response).isNotNull();

        emailService.sendMail();
    }
}