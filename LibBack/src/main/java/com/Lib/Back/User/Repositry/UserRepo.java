package com.Lib.Back.User.Repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Lib.Back.User.Model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUserName(String userName);
    Optional<UserModel> findByEmail(String email);  
    Optional<UserModel> findByUserNameOrEmail(String userName, String email);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);  
}
