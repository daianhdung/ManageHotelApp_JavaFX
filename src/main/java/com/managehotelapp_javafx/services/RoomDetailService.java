package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.entity.BookingEntity;
import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.entity.RoomEntity;

import java.util.List;
import java.util.Set;

public interface RoomDetailService {
    List<RoomEntity> getRoomListByNames(Set<String> names);
}
