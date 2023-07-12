package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserRepository extends GenericRepository<UserEntity>  {
    public List<UserEntity> getUsers();

    public UserEntity getUserById (int idUser);

    public boolean createUser(UserEntity user);

    public boolean checkUser(String username, String password);

    public boolean updateUser(UserEntity user, int idUser);

    public boolean deleteUser(int idUser);



}
