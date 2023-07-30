package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.BookingServiceEntity;
import com.managehotelapp_javafx.entity.UserStatusEntity;

import java.util.List;

public interface BookingServiceRepository extends GenericRepository<BookingServiceEntity> {
    List<BookingServiceEntity> getBookingServiceList();
    List<BookingServiceEntity> getBookingServiceListByBookingId( int id);
    BookingServiceEntity findBookingServiceByIdBookingRoom(int id);

    List<BookingServiceEntity> findBookingServiceByServiceId( int id);
    
}
