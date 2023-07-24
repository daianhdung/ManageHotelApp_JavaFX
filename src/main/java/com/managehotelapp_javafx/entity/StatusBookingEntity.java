package com.managehotelapp_javafx.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "status_booking")
public class StatusBookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 30, nullable = false, unique = true)
    private String title;

    @Column(name = "description", length =30, nullable = false)
    private String description;

    @OneToMany(mappedBy = "statusBooking")
    private Set<BookingRoomEntity> bookingEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<BookingRoomEntity> getBookingEntities() {
        return bookingEntities;
    }

    public void setBookingEntities(Set<BookingRoomEntity> bookingEntities) {
        this.bookingEntities = bookingEntities;
    }
}
