package project.capstone.studyPal.dto.response;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
}
