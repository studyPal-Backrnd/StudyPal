package project.capstone.studyPal.config.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import project.capstone.studyPal.config.security.util.JwtUtil;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.response.LoginResponse;
import project.capstone.studyPal.exception.LogicException;

import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class StudyPalAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private ObjectMapper mapper;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        mapper = new ObjectMapper();
        AppUser user;
        try {
            user = mapper.readValue(request.getInputStream(), AppUser.class);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            Authentication authenticationResult =
                    authenticationManager.authenticate(authentication);
            if (authenticationResult != null) return getAuthentication(authenticationResult);
        } catch (IOException e) {
            throw new LogicException(e.getMessage());
        }
        throw new LogicException("oops!");
    }

    private static Authentication getAuthentication(Authentication authenticationResult) {
        SecurityContextHolder.getContext().setAuthentication(authenticationResult);
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        Map<String, Object> claims = new HashMap<>();
        authResult.getAuthorities().forEach(role -> claims.put("claims", role));
        String email = (String) authResult.getPrincipal();

        String accessToken = generateAccessToken(claims, email);

        String refreshToken = generateRefreshToken(email);

        LoginResponse loginResponse = LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

//        Map<String, String> tokens = new HashMap<>();
//        tokens.put("access_token", accessToken);
//        tokens.put("refresh_token", refreshToken);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), loginResponse);
    }

    private String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuer("study_pal")
                .setExpiration(Date.from(Instant.now().plusSeconds(3600 * 24)))
                .signWith(SignatureAlgorithm.HS512, jwtUtil.getJwtSecret())
                .compact();
    }

    private String generateAccessToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setSubject(email)
                //.set
                .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
                .signWith(SignatureAlgorithm.HS512, jwtUtil.getJwtSecret())
                .setIssuer("study_pal")
                .setIssuedAt(new Date())
                .compact();
    }


}
