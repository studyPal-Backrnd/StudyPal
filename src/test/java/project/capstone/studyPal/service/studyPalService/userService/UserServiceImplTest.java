package project.capstone.studyPal.service.studyPalService.userService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.request.LoginRequest;
import project.capstone.studyPal.dto.response.UserResponse;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class UserServiceImplTest {
    @Autowired UserService userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getUserByEmail() {
        AppUser foundUser = userService.getUserByEmail("demoko3775@vaband.com");
        assertThat(foundUser.getFirstName()).isEqualTo("Study");
        assertThat(foundUser.getLastName()).isEqualTo("Pal");
    }

    @Test
    void getUserById() {
    }

    @Test
    void register() {
    }

    @Test
    void verifyAccount() {
    }

    @Test
    void login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("demoko3775@vaband.com");
        loginRequest.setPassword("Studypal101@");
        UserResponse response = userService.login(loginRequest);
        assertThat(response.getFirstName()).isEqualTo("Study");
    }

    @Test
    void resetPassword() {
    }

    @Test
    void sendResetPasswordMail() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void testUpdateUser() {
    }

    @Test
    void uploadProfileImage() {
    }
}