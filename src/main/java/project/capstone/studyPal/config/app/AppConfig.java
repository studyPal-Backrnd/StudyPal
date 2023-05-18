package project.capstone.studyPal.config.app;

import project.capstone.studyPal.data.models.MailCredential;
import project.capstone.studyPal.dto.request.SendMailRequest;
import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class AppConfig {

    @Value("${mailSendingName}")
    private String hostMailName;

    @Value("${spring.mail.username}")
    private String hostMailAddress;


    @Bean
    public MailConfig mailConfig() {
        return new MailConfig(mailApiKey, mailUrl);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public MailCredential mailCredential() {
        return new MailCredential();
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary();
    }

    @Bean
    public SendMailRequest sendMailRequest() {
        return new SendMailRequest();
    }

}
