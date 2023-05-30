package project.capstone.studyPal.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static project.capstone.studyPal.util.StudyPalUtils.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(min = 8, max = 20)
    @NotEmpty(message = "field password cannot be empty")
    @NotNull(message = "field password cannot be null")
    @Pattern(message = "invalid password", regexp = PASSWORD_REGEX_STRING)
    private String password;

    @Column(unique = true)
    @NotNull(message = "field email cannot be null")
    @NotEmpty(message = "field email cannot be empty")
    @Email(message = "must be valid email address", regexp = EMAIL_REGEX_STRING)
    private String email;

    @NotNull(message = "field first name cannot be null")
    @NotEmpty(message = "field frist name cannot be empty")
    @Pattern(message = "first name must only be letters", regexp = NAME_REGEX)
    private String firstName;

    @NotNull(message = "field last name cannot be null")
    @NotEmpty(message = "field last name cannot be empty")
    @Pattern(message = "first name must only be letters", regexp = NAME_REGEX)
    private String lastName;

    @CreationTimestamp
    private final LocalDateTime createdDate = LocalDateTime.now();

    private boolean isEnabled = false;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Note> notes;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Shelf shelf;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<StudyPlan> studyPlans;
//    @NotNull(message = "Image cannot be null")
    private String profileImage;

    @Enumerated(EnumType.STRING)
    private Set<Role> role;
}
