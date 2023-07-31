package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.entity.RoomStatusEntity;
import com.managehotelapp_javafx.repository.RoomStatusRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomStatusRepositoryImp extends AbstractRepository<RoomStatusEntity> implements RoomStatusRepository {
    @Override
    public RoomStatusEntity findById(int id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        List<RoomStatusEntity> result = query("FROM RoomStatusEntity WHERE id = :id", parameters);
        return result.isEmpty() ? null : result.get(0);
    }
}
