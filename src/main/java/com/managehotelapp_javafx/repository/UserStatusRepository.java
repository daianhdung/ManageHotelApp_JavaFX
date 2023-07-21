package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.UserStatusEntity;
import com.managehotelapp_javafx.repository.GenericRepository;

import java.util.List;

public interface UserStatusRepository extends GenericRepository<UserStatusEntity> {
    List<UserStatusEntity> getUserStatusList();
    UserStatusEntity findUserStatusById(int idUserStatus);

    UserStatusEntity findUserStatusByTitle( String userStatus);
    
}
