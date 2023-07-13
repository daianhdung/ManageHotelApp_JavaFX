package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.UserDTO;

public interface UserService {

    boolean checkLogin(String username, String password);
}
