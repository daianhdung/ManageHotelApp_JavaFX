package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.UserDTO;
import com.managehotelapp_javafx.dto.UserRoleDTO;
import com.managehotelapp_javafx.entity.UserRoleEntity;
import com.managehotelapp_javafx.repository.UserRoleRepository;
import com.managehotelapp_javafx.repository.imp.UserRoleRepositoryImp;
import com.managehotelapp_javafx.services.UserRoleService;

import java.util.ArrayList;
import java.util.List;


public class UserRoleServiceImp implements UserRoleService {
    UserRoleRepository userRoleRepository = new UserRoleRepositoryImp();
    @Override
    public UserRoleEntity findUserRoleById (int idUserRole) {
        return userRoleRepository.findUserRoleById(idUserRole);
    }

    @Override
    public List<UserRoleDTO> getUserRoleList() {
        List<UserRoleDTO> userRoleDTOList = new ArrayList<>();
        userRoleRepository.getUserRoles().forEach(userRoleEntity ->{
            UserRoleDTO userRoleDTO = new UserRoleDTO();
            userRoleDTO.setId(userRoleEntity.getId());
            userRoleDTO.setTitle(userRoleEntity.getTitle());

            userRoleDTOList.add(userRoleDTO);
        });
        return userRoleDTOList;
    }
}
