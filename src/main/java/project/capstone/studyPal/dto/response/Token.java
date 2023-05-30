package project.capstone.studyPal.dto.response;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.capstone.studyPal.data.models.AppUser;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String otp;
    @OneToOne
    private AppUser user;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDateTime expiryTime = createdAt.plusMinutes(20L);

    public Token() {

    }

    public Token(AppUser user, String otp) {
        this.user = user;
        this.otp = otp;
    }
}
