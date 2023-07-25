package com.managehotelapp_javafx.services;
import com.managehotelapp_javafx.dto.*;
import com.managehotelapp_javafx.entity.BookingEntity;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.dto.BookingServiceDTO;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.entity.BookingEntity;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.entity.BookingServiceEntity;
import com.managehotelapp_javafx.entity.ServiceEntity;

import java.util.List;

public interface BookingService {

    List<BookingDTO> getAllBookings();



    BookingEntity getBookingById(int id);

    List<BookingServiceDTO> findBooingServicesByBookingRoomId (int id);
}
