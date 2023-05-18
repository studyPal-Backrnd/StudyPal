package project.capstone.studyPal.service.studyPalService.userService;

import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.request.UserRegisterRequest;
import project.capstone.studyPal.dto.response.UserResponse;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.exception.RegistrationException;
import com.github.fge.jsonpatch.JsonPatch;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

public interface UserService {
    AppUser getUserByEmail(String email) throws LogicException;
    UserResponse register(UserRegisterRequest userDto) throws RegistrationException;
    UserResponse verifyAccount(AppUser appUser, String verificationCode) throws RegistrationException;
    void sendResetPasswordMailCredential(String email) throws LogicException;
    UserResponse login(String email, String password) throws LogicException;
    void resetPassword(String newPassword) throws RegistrationException, LogicException;
    AppUser updateUser(Long userId, JsonPatch updatePayLoad);
}
