package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserRepository extends GenericRepository<UserEntity>  {
    List<UserEntity> getUsers();

    UserEntity getUserById (int idUser);

    boolean insertUser(UserEntity user);

    UserEntity findUserByUsername(String username);

    boolean updateUser(UserEntity user, int idUser);

    boolean deleteUser(int idUser);



}
