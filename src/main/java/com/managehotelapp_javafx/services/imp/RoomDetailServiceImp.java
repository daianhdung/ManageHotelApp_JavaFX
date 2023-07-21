package com.managehotelapp_javafx.services.imp;
import com.managehotelapp_javafx.dto.*;
import com.managehotelapp_javafx.entity.BookingEntity;
import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.entity.RoomStatusEntity;
import com.managehotelapp_javafx.repository.BookingRepository;
import com.managehotelapp_javafx.repository.CustomerRepository;
import com.managehotelapp_javafx.repository.RoomRepository;
import com.managehotelapp_javafx.repository.imp.BookingRepositoryImp;
import com.managehotelapp_javafx.repository.imp.CustomerRepositoryImp;
import com.managehotelapp_javafx.repository.imp.RoomRepositoryImp;
import com.managehotelapp_javafx.services.RoomDetailService;

import java.util.ArrayList;

public class RoomDetailServiceImp implements RoomDetailService{
    RoomRepositoryImp roomRepository = new RoomRepositoryImp();
    BookingRepositoryImp bookingRepository = new BookingRepositoryImp() ;
    CustomerRepositoryImp customerRepository = new CustomerRepositoryImp();

    @Override
    public boolean checkIn(RoomEntity[] roomList, BookingEntity bookingEntity, CustomerEntity customerEntity) {
        for (RoomEntity r : roomList)
        {
            roomRepository.updateCheckInRoom(r);
        }
        bookingRepository.checkinBookingUpdate(bookingEntity);
        customerRepository.updateCustomerStatus(customerEntity);
        return false;
    }
}
