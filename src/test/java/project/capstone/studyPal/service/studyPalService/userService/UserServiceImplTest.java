package project.capstone.studyPal.service.studyPalService.userService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.dto.request.UserRegisterRequest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class UserServiceImplTest {
    @Autowired UserServiceImpl userService;
    private UserRegisterRequest userRegisterRequest;

    @BeforeEach
    void setUp() {
        userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setFirstName("Study");
        userRegisterRequest.setLastName("Pal");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setEmail("osodavid001@gmail.com");
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void register() {
        String response = userService.register(userRegisterRequest);
        assertThat(response).isEqualTo("Account created. Check your email to verify your account");
    }

    @Test
    void verifyAccount() {
        String response = userService.verifyAccount("8607");
        assertThat(response).isEqualTo("Account verified");
    }

    @Test
    void sendResetPasswordMailCredential() {
    }

    @Test
    void login() {
    }

    @Test
    void resetPassword() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void uploadProfileImage() {
    }
}