package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.dto.RoomTypeDTO;
import com.managehotelapp_javafx.entity.RoomEntity;

import java.util.List;

public interface RoomTypeService {

    List<RoomTypeDTO> getAllRoom();

    RoomTypeDTO getRoomById(int id);

}
