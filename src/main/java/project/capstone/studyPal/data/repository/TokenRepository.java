package project.capstone.studyPal.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.response.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findTokenByUserAndOtp(AppUser user, String otp);
    Optional<Token> findTokenByUser(AppUser user);
}
