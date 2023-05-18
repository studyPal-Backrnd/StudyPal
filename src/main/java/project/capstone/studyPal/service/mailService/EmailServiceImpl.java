package project.capstone.studyPal.service.mailService;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.config.app.MailConfig;

@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final MailConfig mailConfig;

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("owoblowpeter@gmail.com");
        simpleMailMessage.setTo("peterowolabi2407@gmail.com");
        simpleMailMessage.setSubject("Activate Account");
        simpleMailMessage.setText("To activate your Study Pal account enter the following digits on your web browser");

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


