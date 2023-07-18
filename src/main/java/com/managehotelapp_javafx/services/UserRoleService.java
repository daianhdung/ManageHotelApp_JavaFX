package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.UserRoleDTO;
import com.managehotelapp_javafx.entity.UserRoleEntity;

import java.util.List;

public interface UserRoleService {
    UserRoleEntity findUserRoleById(int idUserRole);
    List<UserRoleDTO> getUserRoleList ();
 }
