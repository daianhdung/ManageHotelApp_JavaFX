package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.BookingServiceDTO;

import java.util.List;

public interface BookingServicesService {

    boolean insertBookingService(List<BookingServiceDTO> bookingServiceDTOList, List<BookingServiceDTO> bookingServiceDelete, int orderFee);

    List<BookingServiceDTO> findBooingServicesByBookingRoomId (int id);

    boolean deleteBookingService(List<BookingServiceDTO> bookingService);
}
