package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.BookingEntity;

import java.util.List;

import java.util.Map;

public interface BookingRepository extends GenericRepository<BookingEntity> {

    List<BookingEntity> findAllBooking();

    BookingEntity findBookingById (int idBooking);

    boolean updateBooking(BookingEntity bookingEntity);
}
