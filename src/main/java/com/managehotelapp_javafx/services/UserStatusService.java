package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.UserRoleDTO;
import com.managehotelapp_javafx.dto.UserStatusDTO;
import com.managehotelapp_javafx.entity.UserStatusEntity;

import java.util.List;

public interface UserStatusService {

    UserStatusEntity findUserStatusById(int idUserStatus);


    UserStatusEntity findUserStatusByTitle(String userStatus);

    List<UserRoleDTO> getUserStatusList();

}
