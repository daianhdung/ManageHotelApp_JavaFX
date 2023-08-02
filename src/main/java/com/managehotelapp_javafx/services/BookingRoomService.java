package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.dto.InvoiceDTO;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.utils.enumpackage.BookingStatus;

import java.util.List;

public interface BookingRoomService {

    List<BookingRoomDTO> getAllBookingsRoom();

    BookingRoomDTO getBookingRoomById(int id);

    List<BookingRoomDTO> getCurrentBookingRoom();

    List<BookingRoomDTO> getBookingRoomHistory();
  
    BookingRoomEntity getBookingRoomByIdInvoice(int id);

    List<RoomDTO> getRoomByIdBooking (int idBooking);

    List<BookingRoomDTO> getBookingRoomByIdBooking(int id);

    boolean updateBookingRoom(BookingRoomDTO bookingRoomDTO);

    boolean checkOutRoom(List<BookingRoomDTO> bookingRoomDTO, InvoiceDTO invoiceDTO);

    boolean changeStatusRoom(List<BookingRoomDTO> bookingRoomDTOS, BookingStatus bookingStatus);
}
