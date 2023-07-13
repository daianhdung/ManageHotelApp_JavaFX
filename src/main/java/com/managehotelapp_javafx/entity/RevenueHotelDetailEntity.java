package com.managehotelapp_javafx.entity;

import com.managehotelapp_javafx.entity.id.RevenueHotelDetailId;

import javax.persistence.*;

@Entity
@Table(name = "revenue_hotel_detail")
@IdClass(RevenueHotelDetailId.class)
public class RevenueHotelDetailEntity {

    @Id
    private int revenueMonthId;

    @Id
    private int hotelId;

    @Column(name = "amount_revenue")
    private double amountRevenue;

    @ManyToOne
    @JoinColumn(name = "revenue_month_id", insertable = false, updatable = false)
    private RevenueMonthEntity revenueMonth;

    @ManyToOne
    @JoinColumn(name = "hotel_id", insertable = false, updatable = false)
    private HotelEntity hotel;

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

    public double getAmountRevenue() {
        return amountRevenue;
    }

    public void setAmountRevenue(double amountRevenue) {
        this.amountRevenue = amountRevenue;
    }

    public RevenueMonthEntity getRevenueMonth() {
        return revenueMonth;
    }

    public void setRevenueMonth(RevenueMonthEntity revenueMonth) {
        this.revenueMonth = revenueMonth;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }
}
