package project.capstone.studyPal.controller;

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
public class UserController {

 private final   UserService userService;

 @PostMapping("registerUser")
 public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest userDto){
  String registerResponse = userService.register(userDto);
  return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
 }

@PostMapping("verify")
 public ResponseEntity <?> verifyAccount(@RequestParam String verificationToken){
  String response = userService.verifyAccount(verificationToken);
  return ResponseEntity.ok(response);
}

@GetMapping("get")
 public ResponseEntity<?> getUserByEmail(@RequestParam String email){
  AppUser foundUser = userService.getUserByEmail(email);
  return ResponseEntity.status(HttpStatus.OK).body(foundUser);
}
@GetMapping("{userId}")
 public ResponseEntity<?> getUserById(@PathVariable Long userId){
  AppUser foundUser = userService.getUserById(userId);
  return ResponseEntity.status(HttpStatus.OK).body(foundUser);
}
}
