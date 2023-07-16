package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.BookingRoomDTO;

import java.util.List;

public interface BookingRoomService {

    List<BookingRoomDTO> getAllBookingsRoom();
}
