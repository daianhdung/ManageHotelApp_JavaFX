package com.managehotelapp_javafx.entity.id;

import javax.persistence.Column;
import java.io.Serializable;


public class BookingServiceId implements Serializable {

    @Column(name = "booking_room_id")
    private int bookingRoomId;
    @Column(name = "service_id")
    private int serviceId;

    public BookingServiceId() {
    }

    public BookingServiceId(int bookingRoomId, int serviceId) {
        this.bookingRoomId = bookingRoomId;
        this.serviceId = serviceId;
    }

    public int getBookingRoomId() {
        return bookingRoomId;
    }

    public void setBookingRoomId(int bookingRoomId) {
        this.bookingRoomId = bookingRoomId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
