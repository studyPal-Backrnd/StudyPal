package project.capstone.studyPal.service.studyPalService.userService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.data.models.MyToken;
import project.capstone.studyPal.data.repository.UserRepository;
import project.capstone.studyPal.dto.request.EmailNotificationRequest;
import project.capstone.studyPal.dto.request.Recipient;
import project.capstone.studyPal.dto.request.UserRegisterRequest;
import project.capstone.studyPal.dto.response.UserResponse;
import project.capstone.studyPal.exception.*;
import project.capstone.studyPal.service.MailService.MailService;
import project.capstone.studyPal.service.cloudService.CloudService;
import project.capstone.studyPal.service.myTokenService.MyTokenService;

import java.security.SecureRandom;
import java.time.LocalTime;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CloudService cloudService;
    private final ModelMapper mapper;
    private final MailService mailService;
    private final MyTokenService myTokenService;

    @Override
    public AppUser getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new NotFoundException(String.format("User with email %s not found", email)));
    }

    @Override
    public AppUser getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found.."));
    }

    @Override
    public String register(UserRegisterRequest userDto) {
        AppUser appUser = mapper.map(userDto, AppUser.class);
        validateEmail(appUser.getEmail());
        AppUser savedAppUser = userRepository.save(appUser);
        sendVerificationMail(savedAppUser);
        return "Account created. Check your email to verify your account";
    }

    @Override
    public String verifyAccount(String verificationToken){
        MyToken receivedToken = myTokenService.findMyTokenByToken(verificationToken);
//        if(LocalTime.now().isBefore(receivedToken.getCreatedAt().plusMinutes(30L))){
        if(LocalTime.now().isAfter(receivedToken.getExpiryTime()))
            throw new RegistrationException("Token is expired.");
        else if(LocalTime.now().isBefore(receivedToken.getExpiryTime())){
            AppUser appUser = receivedToken.getUser();
            appUser.setEnabled(true);
            userRepository.save(appUser);
            myTokenService.deleteVerificationToken(verificationToken);
        }
        else
            throw new RegistrationException("Token is invalid");
        return "Account verified";
    }

    @Override
    public void sendResetPasswordMailCredential(String name, String email){

    }

    @Override
    public String login(String email, String password) throws LogicException {
//        AppUser appUser = userRepository.findByEmail(email);
//        if (appUser == null || !appUser.getPassword().equals(password))
//            throw new LogicException("Email or password incorrect");
//        if (!appUser.isEnabled()) throw new LogicException("verify your account");
//
//        return getUserResponse(appUser);
        return null;
    }

    @Override
    public void resetPassword(String newPassword) throws RegistrationException, LogicException {
//        if (mailCredential.getExpiryTime().isAfter(LocalDateTime.now())) throw new RegistrationException("  ");
//        getUserByEmail(mailCredential.getRecipientEmail()).setPassword(newPassword);
    }

//    @Override
//    public void sendResetPasswordMailCredential(String email) {
//        String oneTimeResetPassword= RandomString.make(45);
//        mailCredential = new MailCredential();
//        mailCredential.setRecipientEmail(email);
//        mailCredential.setToken(oneTimeResetPassword);
//        emailService.sendMail(mailCredential);
//    }

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

    @Override
    public void saveUser(AppUser appUser) {
        userRepository.save(appUser);
    }

    private void updateProfilePicture(String imageUrl, @NotNull AppUser appUser) {
        appUser.setProfileImage(imageUrl);
    }

    private void sendVerificationMail(AppUser appUser) {
            String token = generateVerificationOTP();
            MyToken myToken = MyToken.builder()
                    .token(token)
                    .user(appUser)
                    .build();
            MyToken savedToken = myTokenService.save(myToken);

            EmailNotificationRequest emailNotificationRequest = new EmailNotificationRequest();
            emailNotificationRequest.getTo().add(new Recipient(appUser.getFirstName(), appUser.getEmail()));
            emailNotificationRequest.setSubject("Activate account");
            emailNotificationRequest.setHtmlContent(
                    "To activate your Study Pal account enter the following digits on your web browser  " + savedToken.getToken());

            mailService.sendHtmlMail(emailNotificationRequest);
    }


    private @NotNull String generateVerificationOTP() {
        SecureRandom otp = new SecureRandom();
        return String.valueOf(otp.nextInt(1010, 10000));
    }

//    private void validatePassword(String password) {
//
//    }

    private void validateEmail(String email){
        if(userRepository.findByEmail(email).isPresent())
            throw new UserExistsException(String.format("User with email %s already exists", email));

    }

    private @NotNull UserResponse getUserResponse(@NotNull AppUser appUser) {

        return mapper.map(appUser, UserResponse.class);
    }
}
