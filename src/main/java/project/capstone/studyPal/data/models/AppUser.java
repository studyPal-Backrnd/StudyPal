package project.capstone.studyPal.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static project.capstone.studyPal.util.StudyPalUtils.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String password;

    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    @CreationTimestamp
    private final LocalDateTime createdDate = LocalDateTime.now();

    private boolean isEnabled = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
//@JoinTable(
//        joinColumns = @JoinColumn(
//                name = "app_user_id",
//                foreignKey = @ForeignKey(
//                        foreignKeyDefinition = "foreign key (app_user_id) references app_user(id) ON DELETE CASCADE"
//                )),
//        inverseJoinColumns = @JoinColumn(
//                name = "notes_id",
//                foreignKey = @ForeignKey(
//                        foreignKeyDefinition = "foreign key (notes_id) references note(id) ON DELETE CASCADE"
//                ))
//)
    private List<Note> notes;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Shelf shelf = new Shelf();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
//   @JoinTable(
//           joinColumns = @JoinColumn(
//                   name = "app_user_id",
//                   foreignKey = @ForeignKey(
//                   foreignKeyDefinition = "foreign key (app_user_id) references app_user(id) ON DELETE CASCADE"
//                   )),
//           inverseJoinColumns = @JoinColumn(
//                   name = "study_plans_id",
//                   foreignKey = @ForeignKey(
//                   foreignKeyDefinition = "foreign key (study_plans_id) references study_plan(id) ON DELETE CASCADE"
//                   ))
//   )
    private List<StudyPlan> studyPlans;

//    @NotNull(message = "Image cannot be null")
    private String profileImage;

    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;






    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
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
        return isEnabled;
    }
}
