package project.capstone.studyPal.service.studyPalService.userService;

import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.request.LoginRequest;
import project.capstone.studyPal.dto.request.UserRegisterRequest;
import project.capstone.studyPal.dto.request.VerifyRequest;
import project.capstone.studyPal.dto.response.UserResponse;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.exception.RegistrationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AppUserServiceImplTest {

    @Autowired
    private UserService userService;
    UserRegisterRequest dto = new UserRegisterRequest();
    AppUser testAppUser = new AppUser();

    @BeforeEach
    void setUp() {
        dto.setEmail("samuelshola14@gmail.com");
//        dto.setEmail("owoblowpeter@gmail.com");
        dto.setPassword("testPassword!54");
        dto.setFirstName("Samuel");
        dto.setLastName("Shola");
    }

    @Test
    void registerUserTest() {
        try {
            userService.register(dto);
        } catch (RegistrationException e) {
            throw new RuntimeException(e.getMessage());
        }
//        assertTrue();
    }

    @Test
    void verifyUserAccount() {
        VerifyRequest verifyRequest = new VerifyRequest("samuelshola14@gmail.com", "5218");
        UserResponse appUser;
        try {
            appUser = userService.verifyAccount(verifyRequest);
        } catch (RegistrationException e) {
            throw new RuntimeException(e.getMessage());
        }
        assertTrue(appUser.isEnabled());

    }

    @Test
    void verifyUserAtLogin() {
        LoginRequest loginRequest = new LoginRequest("owoblowpeter@gmail.com", "testPassword34!");
        assertThrows(RuntimeException.class, ()->userService.login(loginRequest));
    }

}