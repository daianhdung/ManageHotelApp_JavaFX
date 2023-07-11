package com.managehotelapp_javafx.entity.id;


import javax.persistence.Column;
import java.io.Serializable;

public class RoomFacilityId implements Serializable {

    @Column(name = "room_id")
    private int roomId;
    @Column(name = "facility_id")
    private int facilityId;

    public RoomFacilityId() {
    }

    public RoomFacilityId(int roomId, int facilityId) {
        this.roomId = roomId;
        this.facilityId = facilityId;
    }

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
}
