package project.capstone.studyPal.service.mailService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import project.capstone.studyPal.dto.request.MailCredential;
import project.capstone.studyPal.dto.request.Recipient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.util.StudyPalUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmailServiceImplTest {
    @Autowired
    private EmailService emailService;
    private JavaMailSender javaMailSender = new JavaMailSenderImpl() {
    };
    private MailCredential mailRequest = new MailCredential();

    @BeforeEach
    void setUp() {


    }

    @Test
    void sendMail() {
        mailRequest.setText("hello");
        mailRequest.setSubject("Greetings");
        mailRequest.setToken("12234");
        mailRequest.setRecipientEmail("peterowolabi2407@gmail.com");


        emailService.sendMail(mailRequest);
    }

    @Test
    void mailIt() {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("owoblowpeter@gmail.com");
        simpleMailMessage.setReplyTo("owoblowpeter@gmail.com");
        simpleMailMessage.setTo("peterowolabi2407@gmail.com");
        simpleMailMessage.setSubject("Greetings");
        simpleMailMessage.setText("Hello there");

        javaMailSender.send(simpleMailMessage);
    }
}