package project.capstone.studyPal.config.app;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import project.capstone.studyPal.dto.request.MailCredential;
import project.capstone.studyPal.service.studyPalService.noteService.NoteService;
import project.capstone.studyPal.service.studyPalService.noteService.NoteServiceImpl;

@Configuration
public class AppConfig {
//    @Value("${mailSendingName}")
    private String hostMailName;

    private String hostMailAddress;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public MailConfig mailConfig() {
//        return new MailConfig(hostMailName, hostMailAddress);
//    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary();
    }

    @Bean
    public MailCredential mailCredential() {
        return new MailCredential();
    }
    @Bean
    public JavaMailSender javaMailSender(){
        return new JavaMailSenderImpl();
    }
}
