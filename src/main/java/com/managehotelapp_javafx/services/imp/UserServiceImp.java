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
import com.managehotelapp_javafx.services.UserService;
import com.managehotelapp_javafx.utils.session.SessionUser;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImp implements UserService {

    UserRepository userRepository = new UserRepositoryImp();
    UserRoleRepository userRoleRepository = new UserRoleRepositoryImp();
    UserStatusRepository userStatusRepository = new UserStatusRepositoryImp();
    @Override
    public boolean checkLogin(String username, String password) {
        UserEntity user = userRepository.findUserByUsername(username);
        if (user != null) {
            if(BCrypt.checkpw(password, user.getPassword())){

                SessionUser.putValue(user.getUsername(), user.getFullName(), user.getUserRole().getDescription());
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
        userRepository.getUsers().forEach(user -> {
            UserDTO userDTO = new UserDTO();

            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setFullName(user.getFullName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhone(user.getPhoneNumber());
            userDTO.setIdentity(user.getIdentity());
            userDTO.setAddress(user.getAddress());
            userDTO.setRole(user.getUserRole().getTitle());
            userDTO.setStatus(user.getUserStatus().getTitle());
            userDTO.setGender(user.getGender());

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
        userEntity.setUserRole(userRoleRepository.findUserRoleByTitle(user.getRole()));
        userEntity.setUserStatus(userStatusRepository.findUserStatusByTitle(user.getStatus()));
        userEntity.setPhoneNumber(user.getPhone());


        return userRepository.updateUser(userEntity);
    }
}
