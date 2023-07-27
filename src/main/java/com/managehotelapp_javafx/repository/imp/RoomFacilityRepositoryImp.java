package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.RoomFacilityEntity;
import com.managehotelapp_javafx.repository.RoomFacilityRepository;

import java.util.List;

public class RoomFacilityRepositoryImp  extends AbstractRepository<RoomFacilityEntity> implements RoomFacilityRepository {
    @Override
    public List<RoomFacilityEntity> findAll() {
        return query("FROM RoomFacilityEntity", null);
    }
}
