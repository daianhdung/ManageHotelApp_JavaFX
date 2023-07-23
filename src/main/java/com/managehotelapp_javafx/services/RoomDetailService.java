package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.entity.BookingEntity;
import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.entity.RoomEntity;

import java.util.List;

public interface RoomDetailService {
    boolean checkIn(BookingDTO bookingDTO);
}
