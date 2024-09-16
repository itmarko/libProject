package com.Lib.Back.User.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Lib.Back.User.Exception.EmailAlreadyExistsException;
import com.Lib.Back.User.Exception.UserAlreadyExistsException;
import com.Lib.Back.User.Exception.UserNotFoundException;
import com.Lib.Back.User.Model.UserModel;
import com.Lib.Back.User.Repositry.UserRepo;

@Service
public class UserService implements IUserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

   
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserModel> getUserModels() {
        return userRepo.findAll();
    }

    @Override
    @Transactional
    public UserModel addUserModel(UserModel userModel) {
        if (userRepo.existsByUserName(userModel.getUserName())) {
            throw new UserAlreadyExistsException("User already exists with username: " + userModel.getUserName());
        }

        if (userRepo.existsByEmail(userModel.getEmail())) {
            throw new EmailAlreadyExistsException("User already exists with email: " + userModel.getEmail());
        }

        // Encode password
        String encodedPassword = passwordEncoder.encode(userModel.getPassWord());
        userModel.setPassWord(encodedPassword);

        return userRepo.save(userModel);
    }

    @Override
    @Transactional
    public UserModel updateUserModel(UserModel userModel, String userName) {
        return userRepo.findByUserName(userName).map(userExist -> {
            userExist.setUserName(userModel.getUserName());
            userExist.setFullName(userModel.getFullName());
            userExist.setEmail(userModel.getEmail());
            userExist.setMoNum(userModel.getMoNum());
            userExist.setPassWord(passwordEncoder.encode(userModel.getPassWord()));
            return userRepo.save(userExist);
        }).orElseThrow(() -> new UserNotFoundException("This user could not be found"));
    }
    @Override
    public UserModel getUserModelByUserNameOrEmail(String userNameOrEmail) throws UserNotFoundException {
        return userRepo.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }


    @Override
    public void deleteUserModel(Long id) {
        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userRepo.deleteById(id);
    }
}
