package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.UserRoleEntity;
import com.managehotelapp_javafx.repository.UserRoleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRoleRepositoryImp extends AbstractRepository<UserRoleEntity> implements UserRoleRepository {
    @Override
    public List<UserRoleEntity> getUserRoles() {
        return query("FROM UserRoleEntity", null);
    }

    @Override
    public UserRoleEntity findUserRoleById(int idUserRole) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", idUserRole);
        List<UserRoleEntity> result = query("FROM UserRoleEntity WHERE id = :id",parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public UserRoleEntity findUserRoleByTitle(String userRole) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", parameters);
        List<UserRoleEntity> result = query("FROM UserRoleEntity WHERE title = :title",parameters);
        return result.isEmpty() ? null : result.get(0);
    }
}
