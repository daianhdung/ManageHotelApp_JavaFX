package com.managehotelapp_javafx.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "service")
    private Set<BookingServiceEntity> bookingServiceEntities;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "status_service_id")
    private StatusService statusService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<BookingServiceEntity> getBookingServiceEntities() {
        return bookingServiceEntities;
    }

    public void setBookingServiceEntities(Set<BookingServiceEntity> bookingServiceEntities) {
        this.bookingServiceEntities = bookingServiceEntities;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusService getStatusService() {
        return statusService;
    }

    public void setStatusService(StatusService statusService) {
        this.statusService = statusService;
    }
}
