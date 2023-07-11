package com.managehotelapp_javafx.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity(name = "hotel")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "num_employee")
    private int numEmployee;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private HotelStatusEntity hotelStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    private Set<RevenueHotelDetailEntity> revenueHotelDetailEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumEmployee() {
        return numEmployee;
    }

    public void setNumEmployee(int numEmployee) {
        this.numEmployee = numEmployee;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public HotelStatusEntity getHotelStatus() {
        return hotelStatus;
    }

    public void setHotelStatus(HotelStatusEntity hotelStatus) {
        this.hotelStatus = hotelStatus;
    }

    public Set<RevenueHotelDetailEntity> getRevenueHotelDetailEntities() {
        return revenueHotelDetailEntities;
    }

    public void setRevenueHotelDetailEntities(Set<RevenueHotelDetailEntity> revenueHotelDetailEntities) {
        this.revenueHotelDetailEntities = revenueHotelDetailEntities;
    }
}
