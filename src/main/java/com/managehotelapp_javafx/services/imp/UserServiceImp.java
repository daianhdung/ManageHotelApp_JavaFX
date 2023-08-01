package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.UserDTO;
import com.managehotelapp_javafx.entity.UserEntity;
import com.managehotelapp_javafx.entity.UserRoleEntity;
import com.managehotelapp_javafx.exception.BadCredentialsException;
import com.managehotelapp_javafx.exception.UsernameNotFoundException;
import com.managehotelapp_javafx.repository.UserRepository;
import com.managehotelapp_javafx.repository.UserRoleRepository;
import com.managehotelapp_javafx.repository.UserStatusRepository;
import com.managehotelapp_javafx.repository.imp.UserRepositoryImp;
import com.managehotelapp_javafx.repository.imp.UserRoleRepositoryImp;
import com.managehotelapp_javafx.repository.imp.UserStatusRepositoryImp;
import com.managehotelapp_javafx.services.UserRoleService;
import com.managehotelapp_javafx.services.UserService;
import com.managehotelapp_javafx.services.UserStatusService;
import com.managehotelapp_javafx.utils.session.SessionUser;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImp implements UserService {

    UserRepository userRepository = new UserRepositoryImp();
    UserRoleService userRoleService = new UserRoleServiceImp();
    UserStatusService userStatusService = new UserStatusServiceImp();
    @Override
    public boolean checkLogin(String username, String password) {
        UserEntity user = userRepository.findUserByUsername(username);
        if (user != null) {
            if(BCrypt.checkpw(password, user.getPassword())){

                SessionUser.putValue(user.getId(), user.getUsername(), user.getFullName(), user.getUserRole().getDescription());
                return true;
            }else {
                throw new BadCredentialsException("Username or password is incorrect");
            }
        }else{
            throw new UsernameNotFoundException("Username not exist");
        }
    }

    @Override
    public boolean insertUser(UserEntity user) {
        if (userRepository.findUserByUsername(user.getUsername()) == null){
            userRepository.insertUser(user);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
        userRepository.getUsers().forEach(userEntity -> {
            UserDTO userDTO = new UserDTO();

            userDTO.setId(userEntity.getId());
            userDTO.setUsername(userEntity.getUsername());
            userDTO.setFullName(userEntity.getFullName());
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setPhone(userEntity.getPhoneNumber());
            userDTO.setIdentity(userEntity.getIdentity());
            userDTO.setAddress(userEntity.getAddress());
            userDTO.setRole(userEntity.getUserRole().getTitle());
            userDTO.setStatus(userEntity.getUserStatus().getTitle());
            userDTO.setGender(userEntity.getGender());

            userDTOList.add(userDTO);
        });
        return userDTOList;
    }

    @Override
    public boolean deleteUser(int idUser) {
        return userRepository.deleteUser(idUser);
    }

    @Override
    public boolean updateUser(UserDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setAddress(user.getAddress());
        userEntity.setEmail(user.getEmail());
        userEntity.setFullName(user.getFullName());
        userEntity.setGender(user.getGender());
        userEntity.setIdentity(user.getIdentity());
        userEntity.setUsername(user.getUsername());
        userEntity.setUserRole(userRoleService.findUserRoleByTitle(user.getRole()));
        userEntity.setUserStatus(userStatusService.findUserStatusByTitle(user.getStatus()));
        userEntity.setPhoneNumber(user.getPhone());


        return userRepository.updateUser(userEntity);
    }
}
