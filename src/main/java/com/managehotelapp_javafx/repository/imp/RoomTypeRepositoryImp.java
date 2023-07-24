package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.entity.RoomTypeEntity;
import com.managehotelapp_javafx.repository.RoomRepository;
import com.managehotelapp_javafx.repository.RoomTypeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomTypeRepositoryImp extends AbstractRepository<RoomTypeEntity> implements RoomTypeRepository {


    @Override
    public List<RoomTypeEntity> findAll() {
        return query("FROM RoomTypeEntity", null);
    }

    @Override
    public RoomTypeEntity findById(int idRoomType) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", idRoomType);
        List<RoomTypeEntity> result = query("FROM RoomTypeEntity WHERE id = :id", parameters);
        return result.isEmpty() ? null : result.get(0);
    }
}
