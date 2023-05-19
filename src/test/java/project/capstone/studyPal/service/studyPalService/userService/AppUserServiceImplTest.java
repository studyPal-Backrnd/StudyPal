package project.capstone.studyPal.service.studyPalService.userService;

import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.request.UserRegisterRequest;
import project.capstone.studyPal.dto.response.UserResponse;
import project.capstone.studyPal.exception.RegistrationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppUserServiceImplTest {

    @Autowired
    private UserService userService;
    UserRegisterRequest dto = new UserRegisterRequest();
    AppUser testAppUser = new AppUser();

    @BeforeEach
    void setUp() {
        dto.setEmail("osodavid001@gmail.com");
        dto.setPassword("testPassword34!");
        dto.setFirstName("David");
        dto.setLastName("Oso");

    }

    @Test
    void registerUserTest() {
        try {
            UserResponse testAppUser = userService.register(dto);
        } catch (RegistrationException e) {
            throw new RuntimeException(e.getMessage());
        }
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