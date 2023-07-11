package com.managehotelapp_javafx.entity.id;

import javax.persistence.Column;
import java.io.Serializable;

public class RevenueHotelDetailId implements Serializable {

    @Column(name = "revenue_month_id")
    private int revenueMonthId;
    @Column(name = "hotel_id")
    private int hotelId;

    public RevenueHotelDetailId() {
    }

    public RevenueHotelDetailId(int revenueMonthId, int hotelId) {
        this.revenueMonthId = revenueMonthId;
        this.hotelId = hotelId;
    }

    public int getRevenueMonthId() {
        return revenueMonthId;
    }

    public void setRevenueMonthId(int revenueMonthId) {
        this.revenueMonthId = revenueMonthId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
