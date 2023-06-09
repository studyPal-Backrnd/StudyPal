package project.capstone.studyPal.controller;

import com.github.fge.jsonpatch.JsonPatch;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.request.LoginRequest;
import project.capstone.studyPal.dto.request.NewPasswordRequest;
import project.capstone.studyPal.dto.request.UserRegisterRequest;
import project.capstone.studyPal.dto.request.VerifyRequest;
import project.capstone.studyPal.dto.response.UserResponse;
import project.capstone.studyPal.service.studyPalService.userService.UserService;
@AllArgsConstructor
@RequestMapping("/api/v1/studypal/user")
@RestController
@CrossOrigin(origins = "*")
public class UserController {

 private final UserService userService;

 @PostMapping("register")
 public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequest userDto) {
  userService.register(userDto);
  return ResponseEntity.status(HttpStatus.CREATED).body("check mail for verification code");
 }

@PostMapping("verify")
 public ResponseEntity <?> verifyAccount(@Valid @RequestBody VerifyRequest verifyRequest) {
 UserResponse response = userService.verifyAccount(verifyRequest);
 return ResponseEntity.ok(response);
}

@GetMapping("get/{id}")
public ResponseEntity<?> getUserById(@Valid @PathVariable Long id){
  AppUser user = userService.getUserById(id);
  return ResponseEntity.status(HttpStatus.OK).body(user);
}

// <<<<<<< davidBranch
 @GetMapping("get/email")
  public ResponseEntity<?> getUserByEmail(@Valid @RequestParam String email){
// =======
// @GetMapping("{name}")
//  public ResponseEntity<?> getUserByEmail(@Valid @PathVariable(value = "name") String name, @Valid @RequestParam String email){
// >>>>>>> inDev
   AppUser foundUser = userService.getUserByEmail(email);
   return ResponseEntity.status(HttpStatus.OK).body(foundUser);
 }

 @PostMapping("sendmail")
 public ResponseEntity<?> sendEmail(@Valid @RequestParam String emailAddress){
  AppUser user = userService.getUserByEmail(emailAddress);
  userService.sendVerificationMail(user);
  return ResponseEntity.status(HttpStatus.OK).body("check your mail");
 }

@PostMapping("login")
 public ResponseEntity <?> loginToAccount(@Valid @RequestBody LoginRequest loginRequest) {
  UserResponse response = userService.login(loginRequest);
 return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
 }

 @GetMapping("resetpassword")
 public ResponseEntity<?> sendResetPasswordLink(@Valid @RequestParam String emailAddress){
  userService.sendResetPasswordMail(emailAddress);
  return ResponseEntity.status(HttpStatus.OK).body("check your mail for password reset link");
 }

 @PostMapping("resetpassword")
 public ResponseEntity<?> changePassword(@Valid @RequestParam String email, @RequestParam String token, @RequestBody @NotNull NewPasswordRequest newPasswordRequest){
  userService.resetPassword(email, token, newPasswordRequest.getPassword());
  return ResponseEntity.status(HttpStatus.OK).body("password changed successfully");
 }

 @PatchMapping(value = "{id}", consumes = {"application/json-patch+json"})
 public ResponseEntity<?> updateUserProfile(@Valid @PathVariable(value = "id") Long userId, @Valid @RequestBody JsonPatch userRegisterRequest) {
  UserResponse updatedUser = userService.updateUser(userId, userRegisterRequest);
  return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
 }

 @PostMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
 public ResponseEntity<?> uploadProfilePicture(@Valid @PathVariable(value = "id") Long userId, @RequestParam(value = "file") MultipartFile profileImage) {
  UserResponse updatedUser = userService.uploadProfileImage(profileImage, userId);
  return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
 }

}
