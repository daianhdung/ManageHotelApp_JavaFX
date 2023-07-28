package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.dto.BookingServiceDTO;
import com.managehotelapp_javafx.entity.BookingEntity;
import com.managehotelapp_javafx.entity.BookingServiceEntity;
import com.managehotelapp_javafx.repository.BookingRepository;
import com.managehotelapp_javafx.repository.BookingServiceRepository;
import com.managehotelapp_javafx.repository.imp.BookingRepositoryImp;
import com.managehotelapp_javafx.repository.imp.BookingServiceRepositoryImp;
import com.managehotelapp_javafx.services.BookingService;

import java.util.ArrayList;
import java.util.List;

public class BookingServiceImp implements BookingService {

    BookingRepository bookingRepository = new BookingRepositoryImp();
    BookingServiceRepository bookingServiceRepository = new BookingServiceRepositoryImp();

    @Override
    public List<BookingDTO> getAllBookings() {
        return null;
    }

    @Override
    public BookingEntity getBookingById(int id) {
        return bookingRepository.findBookingById(id) ;
    }



}
