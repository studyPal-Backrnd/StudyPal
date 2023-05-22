package project.capstone.studyPal.config.app;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.capstone.studyPal.dto.request.MailCredential;

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

}
