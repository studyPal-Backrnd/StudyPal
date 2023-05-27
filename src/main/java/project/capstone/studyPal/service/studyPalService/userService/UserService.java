package project.capstone.studyPal.service.studyPalService.userService;

import org.springframework.web.multipart.MultipartFile;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.request.UserRegisterRequest;
import project.capstone.studyPal.exception.ImageUploadException;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.exception.RegistrationException;
import com.github.fge.jsonpatch.JsonPatch;
public interface UserService {
    AppUser getUserByEmail(String email);
    AppUser getUserById(Long userId);
    String register(UserRegisterRequest userDto) throws RegistrationException;
    String verifyAccount(String verificationToken) throws RegistrationException;
    void sendResetPasswordMailCredential(String name, String email) throws LogicException;
    String login(String email, String password) throws LogicException;
    void resetPassword(String newPassword) throws RegistrationException, LogicException;
    AppUser updateUser(Long userId, JsonPatch updatePayLoad);
    void uploadProfileImage(MultipartFile profileImage, Long userId) throws ImageUploadException;

}
