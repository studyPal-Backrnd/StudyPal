package project.capstone.studyPal.config.security.users;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.capstone.studyPal.data.models.AppUser;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SecureUser implements UserDetails {
    private final AppUser appUser;
    @Override
    public String getUsername() {
        return appUser.getEmail();
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appUser.getRole().getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
