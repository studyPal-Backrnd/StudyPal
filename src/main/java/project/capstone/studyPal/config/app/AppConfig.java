package project.capstone.studyPal.config.app;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.capstone.studyPal.dto.request.MailCredential;

@Configuration
public class AppConfig {

    @Value("${mailSendingName}")
    private String hostMailName;

    @Value("${spring.mail.username}")
    private String hostMailAddress;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public MailConfig mailConfig() {
        return new MailConfig(hostMailName, hostMailAddress);
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary();
    }

    @Bean
    public MailCredential mailCredential() {
        return new MailCredential();
    }

}
