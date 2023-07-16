package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.entity.UserRoleEntity;
import com.managehotelapp_javafx.repository.UserRoleRepository;
import com.managehotelapp_javafx.repository.imp.UserRoleRepositoryImp;
import com.managehotelapp_javafx.services.UserRoleService;


public class UserRoleServiceImp implements UserRoleService {
    UserRoleRepository userRoleRepository = new UserRoleRepositoryImp();
    @Override
    public UserRoleEntity findUserRoleById (int idUserRole) {
        return userRoleRepository.findUserRoleById(idUserRole);
    }
}
