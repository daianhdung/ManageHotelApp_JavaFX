package com.managehotelapp_javafx.entity;

import com.managehotelapp_javafx.entity.id.BookingServiceId;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "booking_service")
@IdClass(BookingServiceId.class)
public class BookingServiceEntity {

    @Id
    private int bookingRoomId;

    @Id
    private int serviceId;

    @Column(name = "status_payment", columnDefinition = "boolean default false")
    private boolean statusPayment;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT now()")
    private Timestamp createdAt;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "booking_room_id", insertable = false, updatable = false)
    private BookingRoomEntity bookingRoom;

    @ManyToOne
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private ServiceEntity service;

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

    public boolean isStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(boolean statusPayment) {
        this.statusPayment = statusPayment;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public BookingRoomEntity getBookingRoom() {
        return bookingRoom;
    }

    public void setBookingRoom(BookingRoomEntity bookingRoom) {
        this.bookingRoom = bookingRoom;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
