package com.managehotelapp_javafx.utils.session;

import com.managehotelapp_javafx.dto.UserDTO;

public class SessionUser {

    private static UserDTO userDTO = null;


    public static void putValue(int id, String username, String fullName, String role){
        userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setFullName(fullName);
        userDTO.setRole(role);
        userDTO.setId(id);
    }
    public static UserDTO getInstance(){
        return userDTO;
    }

    public static void removeSession(){
        userDTO = null;
    }
}
