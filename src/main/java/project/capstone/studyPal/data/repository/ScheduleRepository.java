package project.capstone.studyPal.data.repository;

import project.capstone.studyPal.data.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
