package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.BookingRoomDTO;

import java.util.List;

public interface BookingRoomService {

    List<BookingRoomDTO> getAllBookingsRoom();

    BookingRoomDTO getBookingRoomById(int id);

    List<BookingRoomDTO> getCurrentBookingRoom();

    List<BookingRoomDTO> getBookingRoomHistory();
}
