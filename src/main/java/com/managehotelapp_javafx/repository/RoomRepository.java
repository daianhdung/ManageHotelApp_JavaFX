package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface RoomRepository extends GenericRepository<RoomEntity>{
    List<RoomEntity> getRooms();

    RoomEntity getRoomByRoomName (String roomName);

    boolean createRoom(RoomEntity room);

    RoomEntity findRoomByStatus(String status);

    boolean updateRoom(RoomEntity room);

    boolean deleteRoom(int RoomId);
}
