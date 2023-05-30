package project.capstone.studyPal.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotNull
    private String password;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @CreationTimestamp
    private final LocalDateTime createdDate = LocalDateTime.now();
    private boolean isEnabled = false;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Note> notes;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Shelf shelf = new Shelf();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<StudyPlan> studyPlans;
//    @NotNull(message = "Image cannot be null")
    private String profileImage;
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
