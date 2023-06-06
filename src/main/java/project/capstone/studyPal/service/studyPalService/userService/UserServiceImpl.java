package project.capstone.studyPal.service.studyPalService.userService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.data.models.Role;
import project.capstone.studyPal.data.repository.TokenRepository;
import project.capstone.studyPal.data.repository.UserRepository;
import project.capstone.studyPal.dto.request.*;
import project.capstone.studyPal.dto.response.Token;
import project.capstone.studyPal.dto.response.UserResponse;
import project.capstone.studyPal.exception.ImageUploadException;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.exception.NotFoundException;
import project.capstone.studyPal.exception.RegistrationException;
import project.capstone.studyPal.service.MailService.MailService;
import project.capstone.studyPal.service.cloudService.CloudService;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final MailService mailService;
    private EmailNotificationRequest emailNotificationRequest;
    private Token token;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final CloudService cloudService;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser getUserByEmail(String email) throws LogicException {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new LogicException(String.format("Email %s not found", email)));
    }

    @Override
    public AppUser getUserById(Long userId) throws NotFoundException {
        return userRepository.findById(userId).orElseThrow(
                ()-> new NotFoundException("User not found.."));
    }

    @Override
    public void register(UserRegisterRequest userDto) {
        AppUser appUser = mapper.map(userDto, AppUser.class);
        appUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        try {
            validateEmail(appUser.getEmail());
        } catch (RegistrationException e) {
            throw new RuntimeException(e.getMessage());
        }
        AppUser savedAppUser = userRepository.save(appUser);
        sendVerificationMail(savedAppUser);
    }

//    @Override
//    public String verifyAccount(String verificationToken){
//        MyToken receivedToken = myTokenService.findMyTokenByToken(verificationToken);
////        if(LocalTime.now().isBefore(receivedToken.getCreatedAt().plusMinutes(30L))){
//        if(LocalTime.now().isAfter(receivedToken.getExpiryTime()))
//            throw new RegistrationException("Token is expired.");
//        else if(LocalTime.now().isBefore(receivedToken.getExpiryTime())){
//            AppUser appUser = receivedToken.getUser();
//            appUser.setEnabled(true);
//            userRepository.save(appUser);
//            myTokenService.deleteVerificationToken(verificationToken);
//        }
//        else
//            throw new RegistrationException("Token is invalid");
//        return "Account verified";
//    }

    @Override
    public UserResponse verifyAccount(@NotNull VerifyRequest verifyRequest) throws RegistrationException {
        if (getUserByEmail(verifyRequest.getEmail()) == null) throw new LogicException("invalid email");
        AppUser appUser = getUserByEmail(verifyRequest.getEmail());
        Optional<Token> token = tokenRepository.findTokenByUserAndOtp(appUser, verifyRequest.getVerificationToken());
        if (token.isEmpty()) throw new LogicException("invalid token");
        if (token.get().getExpiryTime().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(token.get());
            throw new LogicException("expired token");
        }
        appUser.setEnabled(true);
        updateUser(appUser);
        appUser.setRoles(new HashSet<>());
        appUser.getRoles().add(Role.STUDENT);
        tokenRepository.delete(token.get());
        return getUserResponse(appUser);
    }

    @Override
    public UserResponse login(LoginRequest loginRequest) throws LogicException {
        AppUser appUser = getUserByEmail(loginRequest.getEmail());
        if (appUser == null || !appUser.getPassword().equals(loginRequest.getEmail())) throw new LogicException("Email or password incorrect");
        if (!appUser.isEnabled()) throw new LogicException("verify your account");
        return getUserResponse(appUser);
    }

    @Override
    public void resetPassword(String newPassword) throws RegistrationException, LogicException {
        if (token.getExpiryTime().isAfter(LocalDateTime.now())) throw new RegistrationException("reset token not found");
        AppUser appUser = getUserByEmail(token.getUser().getEmail());
        appUser.setPassword(newPassword);
        updateUser(appUser);
    }

    @Override
    public void sendResetPasswordMail(String email) {
        AppUser user;
        emailNotificationRequest = new EmailNotificationRequest();
        try {
            user = getUserByEmail(email);
        } catch (LogicException e) {
            throw new RuntimeException(e.getMessage());
        }
        String oneTimeResetPassword = RandomString.make(45);
        token = new Token(user, oneTimeResetPassword);
        Recipient recipient = new Recipient(user.getFirstName(), user.getEmail());
        emailNotificationRequest.getTo().add(recipient);
        emailNotificationRequest.setSubject("Reset Password");
        emailNotificationRequest.setHtmlContent(" " + oneTimeResetPassword);
        mailService.sendHtmlMail(emailNotificationRequest);

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
    public void updateUser(AppUser updatedUser) {
        userRepository.save(updatedUser);
    }

    @Override
    public void uploadProfileImage(MultipartFile profileImage, Long userId) throws ImageUploadException {
        Optional<AppUser> user = userRepository.findById(userId);

        String imageUrl = cloudService.upload(profileImage);
        user.ifPresent(appUser -> updateProfilePicture(imageUrl, appUser));

    }

    @Override
    public void sendVerificationMail(@NotNull AppUser appUser) {
        emailNotificationRequest = new EmailNotificationRequest();
        Recipient recipient = new Recipient(appUser.getFirstName(), appUser.getEmail());
        emailNotificationRequest.getTo().add(recipient);

        emailNotificationRequest.setSubject("Activate Account");
        String otp = generateVerificationOTP();

//        File tokenFile = new File("/src/main/resources/verification-mail.html");
//        FileWriter fileWriter;
//        try {
//            fileWriter = new FileWriter(tokenFile);
//            BufferedWriter writer = new BufferedWriter(fileWriter);
//            writer.write(
//                    "To activate your Study Pal account enter the following digits on your web browser\n\n"
//                            + token);
//            writer.newLine();
//            writer.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        emailNotificationRequest.setHtmlContent(
                "To activate your Study Pal account enter the following digits on your web browser\n\n"
                        + otp);
        Optional<Token> existingToken = tokenRepository.findTokenByUser(appUser);
        existingToken.ifPresent(tokenRepository::delete);
        token = new Token(appUser, otp);
        tokenRepository.save(token);
        mailService.sendHtmlMail(emailNotificationRequest);
    }

    private void updateProfilePicture(String imageUrl, @NotNull AppUser appUser) {
        appUser.setProfileImage(imageUrl);
        updateUser(appUser);
    }


    private @NotNull String generateVerificationOTP() {
        SecureRandom otp = new SecureRandom();
        return String.valueOf(otp.nextInt(1010, 10000));
    }


    private void validateEmail(String email) throws RegistrationException{
        if (userRepository.findByEmail(email).isPresent()) throw new RegistrationException("email already exists");
    }

    private @NotNull UserResponse getUserResponse(@NotNull AppUser appUser) {
        return mapper.map(appUser, UserResponse.class);
    }
}
