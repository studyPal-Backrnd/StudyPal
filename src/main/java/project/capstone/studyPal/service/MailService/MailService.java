package project.capstone.studyPal.service.MailService;

import project.capstone.studyPal.dto.request.EmailNotificationRequest;

public interface MailService {
    String sendHtmlMail(EmailNotificationRequest request);
}
