package project.capstone.studyPal.service.studyPalService.userService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.data.repository.UserRepository;
import project.capstone.studyPal.dto.request.UserRegisterRequest;
import project.capstone.studyPal.dto.response.UserResponse;
import project.capstone.studyPal.exception.ImageUploadException;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.exception.NotFoundException;
import project.capstone.studyPal.exception.RegistrationException;
import project.capstone.studyPal.service.cloudService.CloudService;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CloudService cloudService;
    private final ModelMapper mapper;

    @Override
    public AppUser getUserByEmail(String email) throws LogicException {
        AppUser foundUser =  userRepository.findByEmail(email);
        if (foundUser == null) throw new LogicException(String.format("Passenger with email %s not found", email));
        return foundUser;
    }

    @Override
    public AppUser getUserById(Long userId) throws NotFoundException {
        return userRepository.findById(userId).orElseThrow(
                ()-> new NotFoundException("User not found.."));
    }

    @Override
    public void register(UserRegisterRequest userDto) {
        AppUser appUser = mapper.map(userDto, AppUser.class);
        try {
            validateEmail(appUser.getEmail());
        } catch (RegistrationException e) {
            throw new RuntimeException(e.getMessage());
        }
        validatePassword(appUser.getPassword());

        AppUser savedAppUser = userRepository.save(appUser);
        sendVerificationMail(savedAppUser.getEmail());
    }

    @Override
    public UserResponse verifyAccount(AppUser appUser, String verificationCode) throws RegistrationException {
//        if (mailCredential.getExpiryTime().isAfter(LocalDateTime.now())) throw new RegistrationException("invalid verification code");
//        if (!mailCredential.getRecipientEmail().equals(appUser.getEmail())) throw new RegistrationException("invalid user");
//        if (!mailCredential.getToken().equals(verificationCode)) throw new RegistrationException("invalid verification code");
        appUser.setEnabled(true);

        return getUserResponse(appUser);
    }

    @Override
    public UserResponse login(String email, String password) throws LogicException {
        AppUser appUser = userRepository.findByEmail(email);
        if (appUser == null || !appUser.getPassword().equals(password)) throw new LogicException("Email or password incorrect");
        if (!appUser.isEnabled()) throw new LogicException("verify your account");

        return getUserResponse(appUser);
    }

    @Override
    public void resetPassword(String newPassword) throws RegistrationException, LogicException {
//        if (mailCredential.getExpiryTime().isAfter(LocalDateTime.now())) throw new RegistrationException("  ");
//        getUserByEmail(mailCredential.getRecipientEmail()).setPassword(newPassword);
    }

    @Override
    public void sendResetPasswordMailCredential(String email) {
        String oneTimeResetPassword= RandomString.make(45);
//        mailCredential = new MailCredential();
//        mailCredential.setRecipientEmail(email);
//        mailCredential.setToken(oneTimeResetPassword);
//        emailService.sendMail(mailCredential);
    }

    @Override
    public AppUser updateUser(Long userId, @NotNull JsonPatch updatePayload) {
        ObjectMapper mapper = new ObjectMapper();
        AppUser foundUser = userRepository.getReferenceById(userId);
        JsonNode node = mapper.convertValue(foundUser, JsonNode.class);
        try {
            JsonNode updatedNode = updatePayload.apply(node);
            var updatedUser = mapper.convertValue(updatedNode, AppUser.class);
            updatedUser = userRepository.save(updatedUser);
            return updatedUser;

        } catch (JsonPatchException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void uploadProfileImage(MultipartFile profileImage, Long userId) throws ImageUploadException {
        Optional<AppUser> user = userRepository.findById(userId);

        String imageUrl = cloudService.upload(profileImage);
        user.ifPresent(appUser -> updateProfilePicture(imageUrl, appUser));

    }

    private void updateProfilePicture(String imageUrl, @NotNull AppUser appUser) {
        appUser.setProfileImage(imageUrl);
    }

    private void sendVerificationMail(String email) {
//        mailCredential = new MailCredential();
//        mailCredential.setRecipientEmail(email);
//        mailCredential.setToken(generateVerificationOTP());
//        mailCredential.setSubject("Activate Account");
//        mailCredential.setText(
//                "To activate your Study Pal account enter the following digits on your web browser\n\n"
//                        + mailCredential.getToken());
//        emailService.sendMail(mailCredential);
    }


    private @NotNull String generateVerificationOTP() {
        SecureRandom otp = new SecureRandom();
        return String.valueOf(otp.nextInt(1010, 10000));
    }

    private void validatePassword(String password) {

    }

    private void validateEmail(String email) throws RegistrationException{
        if (userRepository.findByEmail(email) != null) throw new RegistrationException("email already exists");
    }

    private @NotNull UserResponse getUserResponse(@NotNull AppUser appUser) {

        return mapper.map(appUser, UserResponse.class);
    }
}
