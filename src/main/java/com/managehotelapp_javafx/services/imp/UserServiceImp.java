package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.UserDTO;
import com.managehotelapp_javafx.entity.UserEntity;
import com.managehotelapp_javafx.exception.BadCredentialsException;
import com.managehotelapp_javafx.exception.UsernameNotFoundException;
import com.managehotelapp_javafx.repository.UserRepository;
import com.managehotelapp_javafx.repository.imp.UserRepositoryImp;
import com.managehotelapp_javafx.services.UserService;
import com.managehotelapp_javafx.utils.session.SessionUser;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImp implements UserService {

    UserRepository userRepository = new UserRepositoryImp();

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
}
