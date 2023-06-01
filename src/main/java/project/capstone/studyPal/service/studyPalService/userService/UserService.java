package project.capstone.studyPal.service.studyPalService.userService;

import org.springframework.web.multipart.MultipartFile;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.request.LoginRequest;
import project.capstone.studyPal.dto.request.UserRegisterRequest;
import project.capstone.studyPal.dto.request.VerifyRequest;
import project.capstone.studyPal.dto.response.Token;
import project.capstone.studyPal.dto.response.UserResponse;
import project.capstone.studyPal.exception.ImageUploadException;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.exception.NotFoundException;
import project.capstone.studyPal.exception.RegistrationException;
import com.github.fge.jsonpatch.JsonPatch;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

public interface UserService {
    AppUser getUserByEmail(String email) throws LogicException;
    AppUser getUserById(Long userId) throws NotFoundException;
    void register(UserRegisterRequest userDto) throws RegistrationException;
    UserResponse verifyAccount(VerifyRequest verifyRequest) throws RegistrationException;
    void sendResetPasswordMail(String email) throws LogicException;
    UserResponse login(LoginRequest loginRequest) throws LogicException;
    void resetPassword(String newPassword) throws RegistrationException, LogicException;
    AppUser updateUser(Long userId, JsonPatch updatePayLoad);
    void updateUser(AppUser user);
    void uploadProfileImage(MultipartFile profileImage, Long userId) throws ImageUploadException;
    void sendVerificationMail(@NotNull AppUser appUser);


}
