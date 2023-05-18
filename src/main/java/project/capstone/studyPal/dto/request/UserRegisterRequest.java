package project.capstone.studyPal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import static project.capstone.studyPal.util.StudyPalUtils.EMAIL_REGEX_STRING;
import static project.capstone.studyPal.util.StudyPalUtils.PASSWORD_REGEX_STRING;


@Data
public class UserRegisterRequest {
    @NotNull(message = "field email cannot be null")
    @NotEmpty(message = "field email cannot be empty")
    @Email(message = "must be valid email address", regexp = EMAIL_REGEX_STRING)
    @JsonProperty("email")
    private String email;

    @Size(min = 8, max = 20)
    @NotEmpty
    @NotNull
    @JsonProperty("password")
    @Email(message = "invalid password", regexp = PASSWORD_REGEX_STRING)
    private String password;

    @NotNull(message = "field name cannot be null")
    @NotEmpty(message = "field name cannot be empty")
    private String firstName;

    @NotNull(message = "field name cannot be null")
    @NotEmpty(message = "field name cannot be empty")
    private String lastName;

}
