package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.UserStatusEntity;
import com.managehotelapp_javafx.repository.UserStatusRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStatusRepositoryImp extends AbstractRepository<UserStatusEntity> implements UserStatusRepository {
    @Override
    public List<UserStatusEntity> getUserStatusList() {
        return query("FROM UserStatusEntity ",null);
    }

    @Override
    public UserStatusEntity findUserStatusById(int idUserStatus) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", idUserStatus);
        List<UserStatusEntity> result = query("FROM UserStatusEntity WHERE id = :id",parameters);
        return result.isEmpty() ? null : result.get(0);
    }
}
