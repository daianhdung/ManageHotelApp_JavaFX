package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.entity.UserEntity;
import com.managehotelapp_javafx.repository.RoomRepository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomRepositoryImp extends AbstractRepository<RoomEntity> implements RoomRepository {

    public boolean updateCheckInRoom(RoomEntity roomEntity)
    {
        if(roomEntity.getRoomStatus().getId()==2) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("room_status_id", 1);
            parameters.put("id", roomEntity.getId());
            StringBuffer query = new StringBuffer("UPDATE RoomEntity SET " +
                    "room_status_id = :room_status_id" +
                    " WHERE id = :id");

            return update(query.toString(), parameters);
        }
        return  false;
    }

    public List<RoomEntity> getAvailableRooms() {
        List<RoomEntity> result = query("FROM RoomEntity WHERE room_status_id=2",null);
        return result;
    }

    public List<RoomEntity> getUnavailableRooms() {
        List<RoomEntity> result = query("FROM RoomEntity WHERE room_status_id!=2",null);
        return result;
    }

    @Override
    public List<RoomEntity> getRooms() {
        List<RoomEntity> result = query("FROM RoomEntity",null);
        return result;
    }

    @Override
    public RoomEntity getRoomByRoomName(String roomName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("room_number", roomName);
        List<RoomEntity> result = query("FROM RoomEntity WHERE room_number = :room_number", parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public boolean createRoom(RoomEntity room) {
        return false;
    }

    @Override
    public RoomEntity findRoomByStatus(String status) {
        return null;
    }

    @Override
    public boolean updateRoom(RoomEntity room) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", room.getId());
        parameters.put("room_number", room.getRoomNumber());
        parameters.put("room_type_id", room.getRoomType().getId());
        StringBuffer query = new StringBuffer("UPDATE RoomEntity SET room_number = :room_number" +
                ", room_type_id = :room_type_id" +
                " WHERE id = :id");

        return update(query.toString(),parameters);
    }

    @Override
    public boolean deleteRoom(int RoomId) {
        return false;
    }


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
