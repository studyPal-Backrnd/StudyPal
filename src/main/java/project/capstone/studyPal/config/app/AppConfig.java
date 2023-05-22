package project.capstone.studyPal.config.app;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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

    private String cloudName;
    @Value("${cloudinary.api.key}")
    private String apiKey;
    @Value("${cloudinary.api.secret}")
    private String apiSecret;

    @Value("${google.books.url}")
    private String booksUrl;
    @Value("${google.books.api.key}")
    private String booksApiKey;

    private String hostMailName;

    private String hostMailAddress;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name",cloudName,
                        "api_key",apiKey,
                        "api_secret",apiSecret
                )
        );
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
