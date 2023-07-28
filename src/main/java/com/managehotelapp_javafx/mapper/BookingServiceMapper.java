package com.managehotelapp_javafx.mapper;

import com.managehotelapp_javafx.dto.BookingServiceDTO;
import com.managehotelapp_javafx.entity.BookingServiceEntity;
import com.managehotelapp_javafx.utils.constant.DateFormatConstant;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingServiceMapper {

    public static BookingServiceEntity toBookingServiceEntity(BookingServiceEntity entity, BookingServiceDTO bookingServiceDTO){
        entity.setServiceId(bookingServiceDTO.getServiceId());
        entity.setBookingRoomId(bookingServiceDTO.getBookingRoomId());
        entity.setQuantity(bookingServiceDTO.getQuantity());
        entity.setTotal(bookingServiceDTO.getTotal());

        // Using SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormatConstant.DATETIME_FORMAT_PATTERN);
        try {
            Date date = sdf.parse(bookingServiceDTO.getOrderDate());
            Timestamp timestamp = new Timestamp(date.getTime());
            entity.setCreatedAt(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
