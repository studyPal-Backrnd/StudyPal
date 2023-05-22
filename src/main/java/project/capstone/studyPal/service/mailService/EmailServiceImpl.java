package project.capstone.studyPal.service.mailService;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.dto.request.MailCredential;
import project.capstone.studyPal.util.StudyPalUtils;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(MailCredential mailCredential) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(StudyPalUtils.STUDY_PAL_EMAIL_ADDRESS);
        simpleMailMessage.setReplyTo(StudyPalUtils.STUDY_PAL_EMAIL_ADDRESS);
        simpleMailMessage.setTo(mailCredential.getRecipientEmail());
        simpleMailMessage.setSubject(mailCredential.getSubject());
        simpleMailMessage.setText(mailCredential.getText());

        javaMailSender.send(simpleMailMessage);
    }

//    @Override
//    public String sendMail(SendMailRequest mailRequest) {
//        return null;
//    }
}


//    @Override
//    public String sendMail(SendMailRequest mailRequest) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("api-key", mailConfig.getApiKey());
//
//        HttpEntity<SendMailRequest> requestEntity = new HttpEntity<>(mailRequest, headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(mailConfig.getMailUrl(), requestEntity, String.class);
//
//        return response.getBody();
//
//    }


