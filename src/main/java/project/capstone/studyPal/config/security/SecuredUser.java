//package project.capstone.studyPal.config.security;
//
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import project.capstone.studyPal.data.models.AppUser;
//
//import java.util.Collection;
//import java.util.List;
//
//@AllArgsConstructor
//public class SecuredUser implements UserDetails {
//    private final AppUser appUser;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(appUser.getRole().name()));
//    }
//
//    @Override
//    public String getPassword() {
//        return appUser.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return appUser.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
