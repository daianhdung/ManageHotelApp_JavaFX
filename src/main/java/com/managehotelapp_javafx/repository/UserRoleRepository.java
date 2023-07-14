package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.UserRoleEntity;

import java.util.List;

public interface UserRoleRepository extends  GenericRepository<UserRoleEntity>{
    List<UserRoleEntity> getUserRoles();
    UserRoleEntity findUserRoleById(int idUserRole);
}
