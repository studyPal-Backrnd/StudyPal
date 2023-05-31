package project.capstone.studyPal.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.studyPal.data.models.AppUser;
import project.capstone.studyPal.dto.request.UserRegisterRequest;
import project.capstone.studyPal.dto.response.UserResponse;
import project.capstone.studyPal.exception.LogicException;
import project.capstone.studyPal.exception.RegistrationException;
import project.capstone.studyPal.service.studyPalService.userService.UserService;
@AllArgsConstructor
@RequestMapping("/api/v1/studypal/")
@RestController
@CrossOrigin(origins = "*")
public class UserController {

 private final UserService userService;

 @PostMapping("registerUser")
 public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequest userDto) {
  userService.register(userDto);
  return ResponseEntity.status(HttpStatus.CREATED).body("check mail for verification code");
 }

//@PostMapping("verify")
// public ResponseEntity <?> verifyAccount(@RequestParam String verificationToken){
//  String response = userService.verifyAccount(verificationToken);
//  return ResponseEntity.ok(response);
// }
@GetMapping("verify")
 public ResponseEntity <?> verifyAccount(@Valid @RequestParam String email, String verificationToken) {
 UserResponse response = userService.verifyAccount(email, verificationToken);
 return ResponseEntity.ok(response);
}

@GetMapping("get")
 public ResponseEntity<?> getUserByEmail(@Valid @RequestParam String email){
  AppUser foundUser = userService.getUserByEmail(email);
  return ResponseEntity.status(HttpStatus.OK).body(foundUser);
}
@GetMapping("{userId}")
 public ResponseEntity<?> getUserById(@Valid @PathVariable Long userId){
  AppUser foundUser = userService.getUserById(userId);
  return ResponseEntity.status(HttpStatus.OK).body(foundUser);
}

@PostMapping("login")
 public ResponseEntity <UserResponse> loginToAccount(@Valid @RequestParam String email, @Valid @RequestParam String password) {
  UserResponse response = userService.login(email, password);
 return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
 }
}
