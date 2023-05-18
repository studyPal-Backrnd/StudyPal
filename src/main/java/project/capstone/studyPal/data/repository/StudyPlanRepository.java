package project.capstone.studyPal.data.repository;

import project.capstone.studyPal.data.models.StudyPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyPlanRepository extends JpaRepository<StudyPlan, Long> {
}
