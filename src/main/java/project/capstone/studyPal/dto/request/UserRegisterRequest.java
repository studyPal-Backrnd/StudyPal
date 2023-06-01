package project.capstone.studyPal.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

import static project.capstone.studyPal.util.StudyPalUtils.*;


@Data
public class UserRegisterRequest {
    @NotNull(message = "field email cannot be null")
    @NotEmpty(message = "field email cannot be empty")
    @Email(message = "must be valid email address", regexp = EMAIL_REGEX_STRING)
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    @Size(min = 8, max = 20)
    @NotEmpty(message = "field password cannot be empty")
    @NotNull(message = "field password cannot be null")
    @Pattern(message = "invalid password", regexp = PASSWORD_REGEX_STRING)
    private String password;

    @NotNull(message = "field first name cannot be null")
    @NotEmpty(message = "field first name cannot be empty")
    @Pattern(message = "first name must only be letters", regexp = NAME_REGEX)
    private String firstName;

    @NotNull(message = "field last name cannot be null")
    @NotEmpty(message = "field last name cannot be empty")
    @Pattern(message = "last name must only be letters", regexp = NAME_REGEX)
    private String lastName;

    @Pattern(message = "upload an image", regexp = IMAGE_REGEX)
    private String image;

}
