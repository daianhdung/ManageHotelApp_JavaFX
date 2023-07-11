package com.managehotelapp_javafx.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity(name = "revenue_month")
public class RevenueMonthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private Date date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "revenueMonth")
    private Set<RevenueHotelDetailEntity> revenueHotelDetailEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<RevenueHotelDetailEntity> getRevenueHotelDetailEntities() {
        return revenueHotelDetailEntities;
    }

    public void setRevenueHotelDetailEntities(Set<RevenueHotelDetailEntity> revenueHotelDetailEntities) {
        this.revenueHotelDetailEntities = revenueHotelDetailEntities;
    }
}
