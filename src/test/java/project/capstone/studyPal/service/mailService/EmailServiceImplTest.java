//package project.capstone.studyPal.service.mailService;
//
//import project.capstone.studyPal.dto.request.MailCredential;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//class EmailServiceImplTest {
//    @Autowired
//    private EmailService emailService;
//    private MailCredential mailRequest;
//
//    @BeforeEach
//    void setUp() {
//        List<Recipient> to = List.of(
//                new Recipient("study pal", "")
//        );
//
////        mailRequest = new SendMailRequest(to, "test sending mail", "Let check if it working");
//    }
//
//    @Test
//    void sendMail() {
////        var response = emailService.sendMail(mailRequest);
////        assertThat(response).isNotNull();
//
////        emailService.sendMail(mailCredential);
//    }
//}