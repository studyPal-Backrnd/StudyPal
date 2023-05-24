package project.capstone.studyPal.service.MailService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.capstone.studyPal.config.app.MailConfig;
import project.capstone.studyPal.dto.request.EmailNotificationRequest;

@Service
@AllArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    private final MailConfig mailConfig;
    @Override
    public String sendHtmlMail(@NotNull EmailNotificationRequest request) {
        request.setSubject("Verification token");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", mailConfig.getApiKey());
        HttpEntity<EmailNotificationRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(mailConfig.getMailUrl(), requestEntity, String.class);
        log.info("res->{}", response);
        return response.getBody();
    }
}
