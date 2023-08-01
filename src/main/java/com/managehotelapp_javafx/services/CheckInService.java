package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.BookingDTO;

public interface CheckInService {
    boolean checkIn(BookingDTO bookingDTO);
}
