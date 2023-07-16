package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.UserDTO;
import com.managehotelapp_javafx.entity.UserEntity;

public interface UserService {

    boolean checkLogin(String username, String password);

    boolean insertUser(UserEntity user);


}
