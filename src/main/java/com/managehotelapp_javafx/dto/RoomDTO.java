package com.managehotelapp_javafx.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RoomDTO {

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

    private int id;
    private String roomNo;
    private String title;
    private String status;
    private String type;

    public int getId() {
        return id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
