package project.capstone.studyPal.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import project.capstone.studyPal.config.security.filters.StudyPalAuthenticationFilter;
import project.capstone.studyPal.config.security.filters.StudyPalAuthorizationFilter;
import project.capstone.studyPal.config.security.util.JwtUtil;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final String[] AUTHENTICATION_WHITE_LIST = {"/api/v1/studypal/register", "/api/v1/studypal/verify", "/api/v1/studypal/login"};
//    private final AuthenticationEntryPoint authenticationEntryPoint;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        UsernamePasswordAuthenticationFilter authenticationFilter = new StudyPalAuthenticationFilter(authenticationManager, jwtUtil);
//        authenticationFilter.setFilterProcessesUrl("/api/v1/studypal/login");
//        return http.csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new StudyPalAuthorizationFilter(jwtUtil), StudyPalAuthenticationFilter.class)
//                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.POST, AUTHENTICATION_WHITE_LIST)
//                .permitAll()
//                .and()
//                .authorizeHttpRequests().anyRequest().authenticated()
//                .and()
//                .build();

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(ConfigurationSource()))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, AUTHENTICATION_WHITE_LIST).permitAll()
                        .anyRequest().authenticated())
                .addFilterAt(login(), StudyPalAuthenticationFilter.class)
                .build();
    }

    private UsernamePasswordAuthenticationFilter login() {
        UsernamePasswordAuthenticationFilter authenticationFilter = new StudyPalAuthenticationFilter(
                authenticationManager, jwtUtil, objectMapper);
        authenticationFilter.setFilterProcessesUrl("/api/v1/studypal/login");
        return authenticationFilter;
    }

    private CorsConfigurationSource ConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
