package project.capstone.studyPal.data.repository;

import project.capstone.studyPal.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
}
