package com.Lib.Back.User.Controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Lib.Back.User.Exception.EmailAlreadyExistsException;
import com.Lib.Back.User.Exception.UserAlreadyExistsException;
import com.Lib.Back.User.Exception.UserNotFoundException;
import com.Lib.Back.User.Model.UserModel;
import com.Lib.Back.User.Service.IUserService;
@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping("/create-user-account")
    public ResponseEntity<?> registerUser(@RequestBody UserModel userModel) {
        if (userModel.getPassWord() == null || userModel.getPassWord().isEmpty()) {
            return ResponseEntity.badRequest().body("Password cannot be null or empty");
        }

        try {
            // Add user model through service
            UserModel newUser = userService.addUserModel(userModel);
            return ResponseEntity.ok(newUser);
        } catch (UserAlreadyExistsException | EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    
    @PostMapping("/log-in")
    public ResponseEntity<?> loginUser(@RequestBody UserModel userModel) {
        try {
            if (userModel.getUserName() == null || userModel.getUserName().isEmpty() ||
                userModel.getPassWord() == null || userModel.getPassWord().isEmpty()) {
                return ResponseEntity.badRequest().body("Username and password cannot be null or empty");
            }

            UserModel existingUser = userService.getUserModelByUserNameOrEmail(userModel.getUserName());

            if (existingUser != null && passwordEncoder.matches(userModel.getPassWord(), existingUser.getPassWord())) {
                // Authentication successful
                return ResponseEntity.ok("Login successful");
            } else {
                // Authentication failed
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Log the exception for debugging
            LoggerFactory.getLogger(UserController.class).error("An error occurred during login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


}
