package project.capstone.studyPal.dto.request;

<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;
=======
>>>>>>> 7b1beb3bbbc7945bac7e79d61a2d59703506e7f4

import java.time.LocalDateTime;

@Getter
@Setter
public class MailCredential {
<<<<<<< HEAD

=======
>>>>>>> 7b1beb3bbbc7945bac7e79d61a2d59703506e7f4
    private String recipientEmail;
    private String token;
    private String subject;
    private String text;
    private final LocalDateTime expiryTime = LocalDateTime.now().plusSeconds(120L);


}
