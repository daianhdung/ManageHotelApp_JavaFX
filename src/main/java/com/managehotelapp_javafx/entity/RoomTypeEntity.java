package com.managehotelapp_javafx.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "room_type")
public class RoomTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "roomType")
    private Set<RoomEntity> roomEntities;

    @Column(name = "price")
    private int price;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<RoomEntity> getRoomEntities() {
        return roomEntities;
    }

    public void setRoomEntities(Set<RoomEntity> roomEntities) {
        this.roomEntities = roomEntities;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
