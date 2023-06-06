package project.capstone.studyPal.config.security.filters;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import project.capstone.studyPal.config.security.util.JwtUtil;
import project.capstone.studyPal.dto.response.ApiResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@AllArgsConstructor
public class StudyPalAuthorizationFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain) throws ServletException, IOException {


        if (request.getServletPath().equals("/api/v1/studypal/login") ||
                request.getServletPath().equals("/api/v1/studypal/register") ||
                request.getServletPath().equals("/api/v1/studypal/resetpassword") ||
                request.getServletPath().equals("/api/v1/studypal/verify") ||
                request.getServletPath().equals("api/v1/studypal/note/create")
        ) {
            filterChain.doFilter(request, response);
        } else {
            String authHeader = request.getHeader(AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {

                String jwt = authHeader.substring("Bearer ".length());

                boolean isValid = Jwts.parser()
                        .setSigningKey(jwtUtil.getJwtSecret())
                        .isSigned(jwt);

                if (isValid) {
                    String email = Jwts.parser()
                            .setSigningKey(jwtUtil.getJwtSecret())
                            .parseClaimsJws(jwt)
                            .getBody()
                            .getSubject();
                    if (email != null) {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null,
                                        userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
//                    List<String> roles = new ArrayList<>();
//                    var jwtMap= Jwts.parser().setSigningKey(jwtUtil.getJwtSecret()).parseClaimsJws(jwt);
//                    jwtMap.getBody().forEach((k, v)->roles.add(v.toString()));

                } else {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(
                            response.getOutputStream(),
                            ApiResponse.builder()
                                    .message("auth failed")
                                    .build()
                    );
                }
                filterChain.doFilter(request, response);
            }
            filterChain.doFilter(request, response);
        }
    }
}

