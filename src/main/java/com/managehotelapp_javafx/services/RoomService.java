package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.dto.RoomDTO;

import java.util.List;

public interface RoomService {

    List<RoomDTO> getAllRoom();
    List<RoomDTO> getAvailableRoom();
    List<RoomDTO> getUnavailableRoom();
    RoomDTO getRoomById(int id);
}
