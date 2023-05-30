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

@GetMapping("verify")
 public ResponseEntity <?> verifyAccount(@RequestParam String verificationToken){
  String response = userService.verifyAccount(verificationToken);
  return ResponseEntity.ok(response);
}

@GetMapping("get")
 public ResponseEntity<?> getUserByEmail(@RequestParam String email){
  AppUser foundUser = userService.getUserByEmail(email);
 System.out.println(email);
  return ResponseEntity.status(HttpStatus.OK).body(foundUser);
}

//@PostMapping("login")
// public ResponseEntity <UserResponse> loginToAccount(@RequestParam String email, @RequestParam String password) throws LogicException {
// return new ResponseEntity<>(userService.login(email, password),  HttpStatus.ACCEPTED);
// }

}
