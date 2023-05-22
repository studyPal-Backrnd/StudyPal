package project.capstone.studyPal.service.mailService;

import project.capstone.studyPal.dto.request.MailCredential;

public interface EmailService {

    void sendMail(MailCredential mailCredential);
}
