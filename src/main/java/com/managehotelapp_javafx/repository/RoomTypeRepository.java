package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.entity.RoomTypeEntity;

import java.util.List;

public interface RoomTypeRepository extends GenericRepository<RoomTypeEntity> {

    List<RoomTypeEntity> findAll();

    RoomTypeEntity findById(int idRoomType);
}
