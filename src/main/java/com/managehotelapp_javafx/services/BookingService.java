package com.managehotelapp_javafx.services;
import com.managehotelapp_javafx.dto.*;
import com.managehotelapp_javafx.entity.BookingEntity;

import com.managehotelapp_javafx.dto.BookingDTO;

import java.util.List;

public interface BookingService {

    List<BookingDTO> getAllBookings();
}
