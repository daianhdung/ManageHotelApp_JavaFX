package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.RoomFacilityEntity;

import java.util.List;

public interface RoomFacilityRepository extends GenericRepository<RoomFacilityEntity> {

    List<RoomFacilityEntity> findAll();
}
