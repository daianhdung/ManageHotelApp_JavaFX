package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.entity.UserStatusEntity;

public interface UserStatusService {

    UserStatusEntity findUserStatusById(int idUserStatus);

}
