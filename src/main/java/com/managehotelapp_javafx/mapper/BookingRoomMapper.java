package com.managehotelapp_javafx.mapper;

import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.entity.BookingEntity;
import com.managehotelapp_javafx.entity.BookingRoomEntity;

import java.text.SimpleDateFormat;

public class BookingRoomMapper {

    public static BookingRoomDTO toBookingRoomDTO(BookingRoomEntity item){
        BookingRoomDTO bookingRoomDTO = new BookingRoomDTO();
        bookingRoomDTO.setId(item.getId());
        bookingRoomDTO.setRoomNo(item.getRoom().getRoomNumber());
        bookingRoomDTO.setPhoneNumber(item.getBooking().getCustomer().getPhone());
        bookingRoomDTO.setCustomerName(item.getBooking().getCustomer().getFullName());
        bookingRoomDTO.setBookingId(item.getBooking().getId());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateFormat.format(item.getBooking().getBookingDate());

        bookingRoomDTO.setBookingDate(formattedDateTime);
        bookingRoomDTO.setStatus(item.getStatusBooking().getTitle());
        return bookingRoomDTO;
    }

    public static BookingRoomEntity toBookingRoomEntity(BookingRoomEntity entity, BookingRoomDTO bookingRoomDTO){
        entity.setId(bookingRoomDTO.getId());
        entity.setPayment(bookingRoomDTO.getTotalExpenses());
//        entity.set
        return entity;
    }
}
