package com.managehotelapp_javafx.entity;

import com.managehotelapp_javafx.entity.id.RoomFacilityId;

import javax.persistence.*;

@Entity
@Table(name = "room_facility")
@IdClass(RoomFacilityId.class)
public class RoomFacilityEntity {

    @Id
    private int roomId;

    @Id
    private int facilityId;

    @Column(name = "number")
    private int number;

    @ManyToOne
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private RoomEntity room;

    @Override
    public String toString() {
        return "RoomFacilityEntity{" +
                "roomId=" + roomId +
                ", facilityId=" + facilityId +
                ", number=" + number +
                ", room=" + room +
                ", facility=" + facility +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "facility_id", insertable = false, updatable = false)
    private FacilityEntity facility;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public FacilityEntity getFacility() {
        return facility;
    }

    public void setFacility(FacilityEntity facility) {
        this.facility = facility;
    }
}
