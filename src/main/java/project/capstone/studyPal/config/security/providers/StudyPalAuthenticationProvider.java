package project.capstone.studyPal.config.security.providers;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudyPalAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        String incomingPassword = authentication.getCredentials().toString();

        UserDetails appUserDetails = userDetailsService.loadUserByUsername(email);

        if (passwordEncoder.matches(incomingPassword, appUserDetails.getPassword()))
            return new UsernamePasswordAuthenticationToken(
                    appUserDetails.getUsername(),
                    appUserDetails.getPassword(),
                    appUserDetails.getAuthorities()
            );

        throw new BadCredentialsException("incorrect username or password");
//        if(appUserDetails.getUsername() == null)
//            throw new BadCredentialsException("Email is invalid");
//        else if(!(passwordEncoder.matches(authentication.getCredentials().toString(), appUserDetails.getPassword())))
//            throw new BadCredentialsException("Password is invalid");
//        else if(passwordEncoder.matches(authentication.getCredentials().toString(), appUserDetails.getPassword()))
//            return new UsernamePasswordAuthenticationToken(appUserDetails.getUsername(), appUserDetails.getPassword(), appUserDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
