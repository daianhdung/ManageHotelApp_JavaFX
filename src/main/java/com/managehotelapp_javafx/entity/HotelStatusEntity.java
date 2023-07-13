package com.managehotelapp_javafx.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotel_status")
public class HotelStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 30, nullable = false, unique = true)
    private String name;

    @Column(name = "description", length =30, nullable = false)
    private String description;

    @OneToMany(mappedBy = "hotelStatus")
    private Set<HotelEntity> hotelEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<HotelEntity> getHotelEntities() {
        return hotelEntities;
    }

    public void setHotelEntities(Set<HotelEntity> hotelEntities) {
        this.hotelEntities = hotelEntities;
    }
}
