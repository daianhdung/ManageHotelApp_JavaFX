package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.dto.InvoiceDTO;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.entity.BookingRoomEntity;

import java.util.List;

public interface BookingRoomService {

    List<BookingRoomDTO> getAllBookingsRoom();

    BookingRoomDTO getBookingRoomById(int id);
    BookingRoomEntity getBookingRoomByIdInvoice(int id);

    List<RoomDTO> getRoomByIdBooking (int idBooking);




}
