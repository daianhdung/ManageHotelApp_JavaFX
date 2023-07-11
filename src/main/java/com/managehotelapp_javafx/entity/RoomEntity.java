package com.managehotelapp_javafx.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "room")
    private Set<BookingRoomEntity> bookingRoomEntities;

    @ManyToOne
    @JoinColumn(name = "room_status_id")
    private RoomStatusEntity roomStatus;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomTypeEntity roomType;

    @Column(name = "room_number")
    private String roomNumber;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private Set<RoomFacilityEntity> roomFacilityEntities;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotelEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<BookingRoomEntity> getBookingRoomEntities() {
        return bookingRoomEntities;
    }

    public void setBookingRoomEntities(Set<BookingRoomEntity> bookingRoomEntities) {
        this.bookingRoomEntities = bookingRoomEntities;
    }

    public RoomStatusEntity getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatusEntity roomStatus) {
        this.roomStatus = roomStatus;
    }

    public RoomTypeEntity getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeEntity roomType) {
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Set<RoomFacilityEntity> getRoomFacilityEntities() {
        return roomFacilityEntities;
    }

    public void setRoomFacilityEntities(Set<RoomFacilityEntity> roomFacilityEntities) {
        this.roomFacilityEntities = roomFacilityEntities;
    }

    public HotelEntity getHotelEntity() {
        return hotelEntity;
    }

    public void setHotelEntity(HotelEntity hotelEntity) {
        this.hotelEntity = hotelEntity;
    }
}
