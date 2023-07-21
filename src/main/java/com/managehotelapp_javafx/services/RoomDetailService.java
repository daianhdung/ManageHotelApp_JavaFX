package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.entity.BookingEntity;
import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.entity.RoomEntity;

public interface RoomDetailService {
    boolean checkIn(RoomEntity[] roomEntity, BookingEntity bookingEntity, CustomerEntity customerEntity);
}
