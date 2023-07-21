package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.entity.UserEntity;
import com.managehotelapp_javafx.repository.RoomRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomRepositoryImp extends AbstractRepository<RoomEntity> implements RoomRepository {


    @Override
    public List<RoomEntity> findAll() {
        return query("FROM RoomEntity", null);
    }

    @Override
    public RoomEntity findById(int idUser) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", idUser);
        List<RoomEntity> result = query("FROM RoomEntity WHERE id = :id", parameters);
        return result.isEmpty() ? null : result.get(0);
    }
}
