package project.capstone.studyPal.config.appConfig;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.capstone.studyPal.config.security.util.JwtUtil;

@Configuration
public class AppConfig {

    @Value("${cloudinary.cloud.name}")
    private String cloudName;
    @Value("${cloudinary.api.key}")
    private String apiKey;
    @Value("${cloudinary.api.secret}")
    private String apiSecret;

    @Value("${google.books.url}")
    private String booksUrl;
    @Value("${google.books.api.key}")
    private String booksApiKey;



    @Value("${mail.api.key}")
    private String mailApiKey;
    @Value("${sendinblue.mail.url}")
    private String mailUrl;
    @Value("${jwt.secret.key}")
    private String jwtSecret;

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil(jwtSecret);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public MailConfig mailConfig(){
        return new MailConfig(mailApiKey, mailUrl);
    }

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

}
