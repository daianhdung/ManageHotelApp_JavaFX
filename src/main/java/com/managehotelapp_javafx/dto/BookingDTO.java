package com.managehotelapp_javafx.dto;

import com.managehotelapp_javafx.controller.CheckInController;

import java.util.ArrayList;
import java.util.List;

public class BookingDTO {

    private int id;
    private String roomNo;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    private String customerName;
    private int userid;
    private String customerIDN;
    private String phoneNumber;
    private String bookingDate;
    private String checkInDate;
    private String checkOutDate;
    private String status;
    private List<BookingRoomDTO> bookingRoomDTOList = new ArrayList<>();
    private List<BookingDTO> bookingDTOList;

    public int getAdultCount() {
        return AdultCount;
    }

    public void setAdultCount(int adultCount) {
        AdultCount = adultCount;
    }

    public int getChildrenCount() {
        return ChildrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        ChildrenCount = childrenCount;
    }

    private int AdultCount;
    private int ChildrenCount;
    private String customerRequest;



    public String getCustomerRequest() {
        return customerRequest;
    }

    public void setCustomerRequest(String customerRequest) {
        this.customerRequest = customerRequest;
    }

    public String getCustomerIDN() {
        return customerIDN;
    }

    public void setCustomerIDN(String customerIDN) {
        this.customerIDN = customerIDN;
    }



    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }



    public List<BookingRoomDTO> getBookingRoomDTOList() {
        return bookingRoomDTOList;
    }

    public void setBookingRoomDTOList(List<BookingRoomDTO> bookingRoomDTOList) {
        this.bookingRoomDTOList = bookingRoomDTOList;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCheckinDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BookingDTO> getBookingDTOList() {
        return bookingDTOList;
    }

    public void setBookingDTOList(List<BookingDTO> bookingDTOList) {
        this.bookingDTOList = bookingDTOList;
    }


    public void setUserId(int userid) {
        this.userid = userid;
    }

    public int getUserId() {
        return userid;
    }
}
