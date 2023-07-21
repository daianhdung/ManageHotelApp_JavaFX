package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.entity.UserEntity;

import java.util.List;

public interface RoomRepository extends GenericRepository<RoomEntity> {

    List<RoomEntity> findAll();

    RoomEntity findById(int idUser);
}
