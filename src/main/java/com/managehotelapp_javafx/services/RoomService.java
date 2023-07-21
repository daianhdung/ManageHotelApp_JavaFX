package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.RoomDTO;

import java.util.List;

public interface RoomService {

    List<RoomDTO> getAllRoom();

    RoomDTO getRoomById(int id);

}
