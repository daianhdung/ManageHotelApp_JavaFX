package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.UserDTO;
import com.managehotelapp_javafx.dto.UserRoleDTO;
import com.managehotelapp_javafx.dto.UserStatusDTO;
import com.managehotelapp_javafx.entity.UserStatusEntity;
import com.managehotelapp_javafx.repository.UserStatusRepository;
import com.managehotelapp_javafx.repository.imp.UserStatusRepositoryImp;
import com.managehotelapp_javafx.services.UserRoleService;
import com.managehotelapp_javafx.services.UserStatusService;

import java.util.ArrayList;
import java.util.List;

public class UserStatusServiceImp implements UserStatusService {
    UserStatusRepository userStatusRepository = new UserStatusRepositoryImp();
    @Override
    public UserStatusEntity findUserStatusById(int idUserStatus) {

        return userStatusRepository.findUserStatusById(idUserStatus) ;
    }

    @Override
    public UserStatusEntity findUserStatusByTitle(String userStatus) {
        return userStatusRepository.findUserStatusByTitle(userStatus) ;
    }


    @Override
    public List<UserRoleDTO> getUserStatusList() {
        List<UserRoleDTO> userRoleDTOList = new ArrayList<>();
        userStatusRepository.getUserStatusList().forEach(userStatusEntity -> {
            UserRoleDTO userRoleDTO = new UserRoleDTO();

            userRoleDTO.setId(userStatusEntity.getId());
            userRoleDTO.setTitle(userStatusEntity.getTitle());

            userRoleDTOList.add(userRoleDTO);
        });

        return userRoleDTOList;
    }
}
