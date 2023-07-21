package com.managehotelapp_javafx.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RoomDTO {
    private String roomname;
    private String roomtype;
    private String roomstatus;

    public Set<String> getRoomList() {
        return roomList;
    }

    public void setRoomList(Set<String> roomList) {
        this.roomList = roomList;
    }
    private final static RoomDTO INSTANCE = new RoomDTO();
    public static RoomDTO getInstance() {
        return INSTANCE;
    }
    private Set<String> roomList = new HashSet<>();

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getRoomstatus() {
        return roomstatus;
    }

    public void setRoomstatus(String roomstatus) {
        this.roomstatus = roomstatus;

    }
}
