package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.BookingRoomEntity;

import java.util.List;

public interface BookingRoomRepository extends GenericRepository<BookingRoomEntity> {

    List<BookingRoomEntity> findAll();

    BookingRoomEntity findById(int id);

    List<BookingRoomEntity> findByStatusBookingIdIsIn(List<Integer> listId);
    BookingRoomEntity findByIdInvoice(int idInv);
    List<BookingRoomEntity> findRoomByIdBooking (int idBooking);
}
