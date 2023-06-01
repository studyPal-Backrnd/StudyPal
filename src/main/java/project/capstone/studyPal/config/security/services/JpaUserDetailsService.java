package project.capstone.studyPal.config.security.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.config.security.users.SecureUser;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.service.studyPalService.userService.UserService;

@AllArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userService.getUserByEmail(username);
        return new SecureUser(user);
    }
}
