package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.RoomStatusEntity;

public interface RoomStatusRepository extends GenericRepository<RoomStatusEntity>{

    RoomStatusEntity findById(int id);
}
