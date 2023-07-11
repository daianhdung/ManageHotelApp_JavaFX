package com.managehotelapp_javafx.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "facility")
public class FacilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 30, nullable = false, unique = true)
    private String name;

    @Column(name = "description", length =30, nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facility")
    private Set<RoomFacilityEntity> roomFacilityEntities;

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

    public Set<RoomFacilityEntity> getRoomFacilityEntities() {
        return roomFacilityEntities;
    }

    public void setRoomFacilityEntities(Set<RoomFacilityEntity> roomFacilityEntities) {
        this.roomFacilityEntities = roomFacilityEntities;
    }
}
