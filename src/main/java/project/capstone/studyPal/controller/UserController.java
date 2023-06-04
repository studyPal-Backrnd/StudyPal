package project.capstone.studyPal.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.request.LoginRequest;
import project.capstone.studyPal.dto.request.NewPasswordRequest;
import project.capstone.studyPal.dto.request.UserRegisterRequest;
import project.capstone.studyPal.dto.request.VerifyRequest;
import project.capstone.studyPal.dto.response.UserResponse;
import project.capstone.studyPal.service.studyPalService.userService.UserService;
@AllArgsConstructor
@RequestMapping("/api/v1/studypal/")
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

@GetMapping("{name}")
 public ResponseEntity<?> getUserByEmail(@Valid @PathVariable(value = "name") String name, @Valid @RequestParam String email){
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

}
