package project.capstone.studyPal.service.mailService;

import project.capstone.studyPal.dto.request.MailCredential;

public interface EmailService {

//    String sendMail(SendMailRequest mailRequest);

    void sendMail(MailCredential mailCredential);
}
