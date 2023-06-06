package project.capstone.studyPal.data.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import static project.capstone.studyPal.data.models.Permission.*;

//@RequiredArgsConstructor
public enum Role {
    STUDENT
//    STUDENT(
//            Set.of(
//            STUDENT_READ,
//            STUDENT_UPDATE,
//            STUDENT_DELETE,
//            STUDENT_CREATE
//            ));
//
//    @Getter
//    private final Set<Permission> permissions;
//
//    public List<SimpleGrantedAuthority> getAuthorities() {
//        var authorities = getPermissions()
//                .stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toList());
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return authorities;
//    }
}
