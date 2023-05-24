package project.capstone.studyPal.service.studyPalService.userService;

import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.request.UserRegisterRequest;
import project.capstone.studyPal.dto.response.UserResponse;
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
        dto.setEmail("owoblowpeter@gmail.com");
        dto.setPassword("testPassword34!");
        dto.setFirstName("David");
        dto.setLastName("Oso");

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
        try {
            UserResponse appUser = userService.verifyAccount(testAppUser, "1234");
        } catch (RegistrationException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}