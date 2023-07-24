package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.BookingEntity;

import java.util.List;

public interface BookingRepository extends GenericRepository<BookingEntity> {

    List<BookingEntity> findAllBooking();

    BookingEntity findBookingById (int idBooking);
}
