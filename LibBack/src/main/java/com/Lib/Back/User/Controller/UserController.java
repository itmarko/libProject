package com.Lib.Back.User.Controller;

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
            // Encode and save user
            String encodedPassword = passwordEncoder.encode(userModel.getPassWord());
            userModel.setPassWord(encodedPassword);

            UserModel newUser = userService.addUserModel(userModel);
            return ResponseEntity.ok(newUser);
        } catch (UserAlreadyExistsException | EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }


    @PostMapping("/log-in")
    public ResponseEntity<?> loginUser(@RequestBody UserModel userModel) {
        try {
            // Validate input
            if (userModel.getUserName() == null || userModel.getUserName().isEmpty() &&
                userModel.getEmail() == null || userModel.getEmail().isEmpty() ||
                userModel.getPassWord() == null || userModel.getPassWord().isEmpty()) {
                return ResponseEntity.badRequest().body("Username, email, and password cannot be null or empty");
            }

            // Retrieve user by username or email
            UserModel existingUser = userService.getUserModelByUserNameOrEmail(userModel.getUserName(), userModel.getEmail());

            // Check if user exists and password matches
            if (existingUser != null && passwordEncoder.matches(userModel.getPassWord(), existingUser.getPassWord())) {
                // Generate a token or some response on successful login
                // String token = generateToken(existingUser); // Example
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Use a logger instead of printStackTrace for better logging management

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

}
