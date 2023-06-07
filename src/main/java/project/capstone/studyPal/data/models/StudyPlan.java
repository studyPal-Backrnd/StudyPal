package project.capstone.studyPal.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyPlanId;
    private String title;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
//    @JoinTable(
//            joinColumns = @JoinColumn(
//                    name = "study_plan_id",
//                    foreignKey = @ForeignKey(
//                            foreignKeyDefinition = "foreign key (study_plan_id) references study_plan(id) ON DELETE CASCADE"
//                    )),
//            inverseJoinColumns = @JoinColumn(
//                    name = "scedules_id",
//                    foreignKey = @ForeignKey(
//                            foreignKeyDefinition = "foreign key (schedules_id) references schedule(id) ON DELETE CASCADE"
//                    ))
//    )

    private Set<Schedule> schedules;
    private LocalDateTime updatedAt;
//    private final LocalDateTime createAt = LocalDateTime.now();
}