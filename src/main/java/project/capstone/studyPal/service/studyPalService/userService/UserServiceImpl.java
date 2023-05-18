package project.capstone.studyPal.service.studyPalService.userService;

import project.capstone.studyPal.config.app.MailConfig;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.data.repository.UserRepository;
import project.capstone.studyPal.dto.request.Recipient;
import project.capstone.studyPal.dto.request.SendMailRequest;
import project.capstone.studyPal.dto.request.UserRegisterRequest;
import project.capstone.studyPal.dto.response.UserResponse;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.exception.RegistrationException;
import project.capstone.studyPal.data.models.MailCredential;

import project.capstone.studyPal.service.mailService.EmailService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private SendMailRequest mailCredential;
    private final UserRepository userRepository;
    private final EmailService emailService;
//    private final MailConfig mailConfig;
    private final ModelMapper mapper;

    @Override
    public AppUser getUserByEmail(String email) throws LogicException {
        AppUser foundUser =  userRepository.findByEmail(email);
        if (foundUser == null) throw new LogicException(String.format("Passenger with email %s not found", email));
        return foundUser;
    }

    @Override
    public UserResponse register(UserRegisterRequest userDto) {
        AppUser appUser = mapper.map(userDto, AppUser.class);
        try {
            validateEmail(appUser.getEmail());
        } catch (RegistrationException e) {
            throw new RuntimeException(e.getMessage());
        }
        validatePassword(appUser.getPassword());

        AppUser savedAppUser = userRepository.save(appUser);
        sendVerificationMail(savedAppUser.getEmail());

        return getUserResponse(savedAppUser);
    }

    @Override
    public UserResponse verifyAccount(AppUser appUser, String verificationCode) throws RegistrationException {
//        if (mailCredential.getExpiryTime().isAfter(LocalDateTime.now())) throw new RegistrationException("invalid verification code");
//        if (!mailCredential.getEmail().equals(appUser.getEmail())) throw new RegistrationException("invalid user");
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
//        getUserByEmail(mailCredential.getEmail()).setPassword(newPassword);
    }

    @Override
    public void sendResetPasswordMailCredential(String email) {
        setMailCredentials(email);
        String oneTimeResetPassword= RandomString.make(45);
        mailCredential.setSubject("login with this password: ");
        mailCredential.setTextContent(oneTimeResetPassword);
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

    private void sendVerificationMail(String email) {
        setMailCredentials(email);
        mailCredential.setSubject("your verification code is: ");
        mailCredential.setTextContent(generateVerificationOTP());
//        emailService.sendMail(mailCredential);
    }

    private void setMailCredentials(String email) {
        AppUser user = new AppUser();
        try {
            user = getUserByEmail(email);
        } catch (LogicException e) {
            throw new RuntimeException(e.getMessage());
        }
        mailCredential = new SendMailRequest();
        Recipient recipient = new Recipient();
        recipient.setName(user.getFirstName() + " " + user.getLastName());
        recipient.setEmail(user.getEmail());
        mailCredential.getTo().add(recipient);
    }

    private @NotNull String generateVerificationOTP() {
        SecureRandom otp = new SecureRandom();
        return String.valueOf(otp.nextInt(1000));
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
