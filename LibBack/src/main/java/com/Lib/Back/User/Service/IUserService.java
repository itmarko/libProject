package com.Lib.Back.User.Service;

import java.util.List;

import com.Lib.Back.User.Model.UserModel;

public interface IUserService {
	UserModel addUserModel(UserModel userModel);

	List<UserModel> getUserModels();

	UserModel updateUserModel(UserModel userModel, String userName);

	UserModel getUserModelByUserNameOrEmail(String userNameOrEmail);

	void deleteUserModel(Long id); 
}
