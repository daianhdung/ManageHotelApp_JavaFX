package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.config.ConnectDB;
import com.managehotelapp_javafx.entity.UserEntity;
import com.managehotelapp_javafx.repository.UserRepository;
import org.mindrot.jbcrypt.*;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryImp extends AbstractRepository<UserEntity> implements UserRepository {
    public static UserRepositoryImp getInstance() {
        return new UserRepositoryImp();
    }

    public boolean checkUser(String username, String password) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", username);


        StringBuffer queryString = new StringBuffer("FROM UserEntity WHERE username = :username");
        boolean checkUserByName = !query(queryString.toString(), parameters).isEmpty();

        if (checkUserByName) {
            UserEntity user = query(queryString.toString(), parameters).get(0);
            boolean checkPassword = BCrypt.checkpw(password, user.getPassword());
            if (checkPassword) return true;
        }

        return false;
    }

    @Override
    public List<UserEntity> getUsers() {
        return query("FROM UserEntity", null);
    }

    @Override
    public UserEntity getUserById(int idUser) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", idUser);
        return query("FROM UserEntity WHERE id = :id", parameters).get(0);
    }

    @Override
    public boolean createUser(UserEntity user) {
        return false;
    }

    @Override
    public boolean updateUser(UserEntity user, int idUser) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", user.getId());
        parameters.put("address", user.getAddress());
        parameters.put("created_at", user.getCreatedAt());
        parameters.put("email", user.getEmail());
        parameters.put("fullname", user.getFullName());
        parameters.put("gender", user.getGender());
        parameters.put("identity", user.getIdentity());
        parameters.put("password", user.getPassword());
        parameters.put("username", user.getUsername());
        parameters.put("user_role_id", user.getUserRole());
        parameters.put("user_status_id", user.getUserStatus());

        StringBuffer query = new StringBuffer("UPDATE UserEntity SET address = :address" +
                ", created_at = :created_at" +
                ", email = :email" +
                ", fullname = :fullname" +
                ", gender = :gender" +
                ", identity = :identity" +
                ", password = :password" +
                ", username = :username" +
                ", user_role_id = :user_role_id" +
                ", user_status_id = :user_status_id" +
                " WHERE id = :id");

        return update(query.toString(),parameters);
    }

    @Override
    public boolean deleteUser(int idUser) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", idUser);

        return delete("DELETE FROM UserEntity WHERE id = :id",parameters);
    }
}
