package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.repository.BookingRoomRepository;
import com.managehotelapp_javafx.repository.imp.BookingRoomRepositoryImp;
import com.managehotelapp_javafx.services.BookingRoomService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookingRoomServiceImp implements BookingRoomService {

    BookingRoomRepository bookingRoomRepository = new BookingRoomRepositoryImp();

    @Override
    public List<BookingRoomDTO> getAllBookingsRoom() {
        List<BookingRoomDTO> bookingRoomDTOList = new ArrayList<>();
        bookingRoomRepository.findAll().forEach(item -> {
            BookingRoomDTO bookingRoomDTO = new BookingRoomDTO();
            bookingRoomDTO.setRoomNo(item.getRoom().getRoomNumber());
            bookingRoomDTO.setPhoneNumber(item.getBooking().getCustomer().getPhone());
            bookingRoomDTO.setCustomerName(item.getBooking().getCustomer().getFullName());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateFormat.format(item.getBooking().getBookingDate());

            bookingRoomDTO.setBookingDate(formattedDateTime);
            bookingRoomDTO.setStatus(item.getBooking().getStatusBooking().getTitle());
            bookingRoomDTOList.add(bookingRoomDTO);
        });
        return bookingRoomDTOList;
    }


}
