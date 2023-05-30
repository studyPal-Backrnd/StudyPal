package project.capstone.studyPal.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.capstone.studyPal.data.models.MyToken;

import java.util.Optional;

public interface MyTokenRepository extends JpaRepository<MyToken, Long> {
    Optional<MyToken> findByToken(String token);
}
