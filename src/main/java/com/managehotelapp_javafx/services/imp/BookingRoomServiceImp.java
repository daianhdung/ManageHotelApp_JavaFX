package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
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
            bookingRoomDTO.setId(item.getId());
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

    @Override
    public BookingRoomDTO getBookingRoomById(int id) {
        BookingRoomEntity bookingRoom = bookingRoomRepository.findById(id);
        BookingRoomDTO bookingRoomDTO = new BookingRoomDTO();
        bookingRoomDTO.setRoomNo(bookingRoom.getRoom().getRoomNumber());
        bookingRoomDTO.setCustomerName(bookingRoom.getBooking().getCustomer().getFullName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateFormat.format(bookingRoom.getBooking().getBookingDate());
        bookingRoomDTO.setBookingDate(formattedDateTime);

        if(bookingRoom.getBooking().getActualDateIn() != null){
            formattedDateTime = dateFormat.format(bookingRoom.getBooking().getActualDateIn());
            bookingRoomDTO.setCheckinDate(formattedDateTime);
        }

        if(bookingRoom.getBooking().getActualDateOut() != null){
            formattedDateTime = dateFormat.format(bookingRoom.getBooking().getActualDateOut());
            bookingRoomDTO.setCheckoutDate(formattedDateTime);
        }


        bookingRoomDTO.setStatus(bookingRoom.getBooking().getStatusBooking().getTitle());
        bookingRoomDTO.setNumAdult(bookingRoom.getBooking().getNumAdult());
        bookingRoomDTO.setNumChildren(bookingRoom.getBooking().getNumChildren());
        bookingRoomDTO.setEmail(bookingRoom.getBooking().getCustomer().getEmail());
        bookingRoomDTO.setPhoneNumber(bookingRoom.getBooking().getCustomer().getPhone());
        bookingRoomDTO.setSpecialRequest(bookingRoom.getBooking().getSpecialRequest());

        return bookingRoomDTO;
    }


}
