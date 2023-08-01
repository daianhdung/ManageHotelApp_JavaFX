package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.RoomFacilityEntity;
import com.managehotelapp_javafx.repository.RoomFacilityRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomFacilityRepositoryImp extends AbstractRepository<RoomFacilityEntity> implements RoomFacilityRepository {


    @Override
    public List<RoomFacilityEntity> findAll() {
        return query("FROM RoomFacilityEntity", null);
    }

    @Override
    public RoomFacilityEntity findById(int id) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
        return query("FROM RoomFacilityEntity WHERE room_id = :id",parameter).get(0);
    }


}
