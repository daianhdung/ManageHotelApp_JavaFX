package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.entity.UserStatusEntity;
import com.managehotelapp_javafx.repository.UserStatusRepository;
import com.managehotelapp_javafx.repository.imp.UserStatusRepositoryImp;
import com.managehotelapp_javafx.services.UserStatusService;

public class UserStatusServiceImp implements UserStatusService {
    UserStatusRepository userStatusRepository = new UserStatusRepositoryImp();
    @Override
    public UserStatusEntity findUserStatusById(int idUserStatus) {
        return userStatusRepository.findUserStatusById(idUserStatus) ;
    }
}
