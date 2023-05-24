package project.capstone.studyPal.controller;

<<<<<<< HEAD
import com.github.fge.jsonpatch.JsonPatch;
=======
>>>>>>> 7b1beb3bbbc7945bac7e79d61a2d59703506e7f4
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
<<<<<<< HEAD
@RequestMapping("/api/v1/studypal/")
=======
@RequestMapping("/api/v1/studyPal/")
>>>>>>> 7b1beb3bbbc7945bac7e79d61a2d59703506e7f4
@RestController
public class UserController {

 private final   UserService userService;

@PostMapping("registerUser")
<<<<<<< HEAD
// @ResponseStatus(HttpStatus.CREATED)
=======
>>>>>>> 7b1beb3bbbc7945bac7e79d61a2d59703506e7f4
 public ResponseEntity <UserResponse> registerUser( UserRegisterRequest userDto) throws RegistrationException {
 return new ResponseEntity<>(userService.register(userDto), HttpStatus.CREATED);
}

@GetMapping("verify")
 public ResponseEntity <UserResponse > verifyAccount(@RequestParam AppUser appUser, @RequestParam String verificationCode) throws RegistrationException {
 return new ResponseEntity<>(userService.verifyAccount(appUser, verificationCode), HttpStatus.OK);
}

<<<<<<< HEAD
@GetMapping("login")
 public ResponseEntity <UserResponse> loginToAccount(@RequestParam String email, @RequestParam String password) throws LogicException {
 return new ResponseEntity<>(userService.login(email, password),  HttpStatus.ACCEPTED);
}
@PostMapping("updateAccount")
 public ResponseEntity<AppUser> updateAccount(@RequestParam long id, @RequestParam JsonPatch updatePayLoad){
 return new ResponseEntity<>(userService.updateUser(id, updatePayLoad), HttpStatus.GONE);
}
@PutMapping("resetPassword")
 public ResponseEntity<String> restPassword(String newPassword) throws RegistrationException, LogicException {
 userService.resetPassword(newPassword);
// return ResponseEntity<>()
 return null;
}

=======
@PostMapping("login")
 public ResponseEntity <UserResponse> loginToAccount(@RequestParam String email, @RequestParam String password) throws LogicException {
 return new ResponseEntity<>(userService.login(email, password),  HttpStatus.ACCEPTED);
}
>>>>>>> 7b1beb3bbbc7945bac7e79d61a2d59703506e7f4

}
