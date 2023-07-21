package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.BookingDTO;

import com.managehotelapp_javafx.repository.BookingRepository;
import com.managehotelapp_javafx.repository.imp.BookingRepositoryImp;
import com.managehotelapp_javafx.services.BookingService;

import java.util.ArrayList;
import java.util.List;

public class BookingServiceImp implements BookingService {

    BookingRepository bookingRepository = new BookingRepositoryImp();

    @Override
    public List<BookingDTO> getAllBookings() {
        return null;
    }

}
