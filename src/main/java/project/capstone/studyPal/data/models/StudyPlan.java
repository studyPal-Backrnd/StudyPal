package project.capstone.studyPal.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(
            joinColumns = @JoinColumn(
                    name = "study_plan_id",
                    foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (study_plan_id) references study_plan(id)")
            ),inverseJoinColumns = @JoinColumn(
                    name = "schedules_id",
                    foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (schedules_id) references schedules(id)"))
    )
    private Set<Schedule> schedules;
    private LocalDate createdDate;
    private LocalDate endDate;
}