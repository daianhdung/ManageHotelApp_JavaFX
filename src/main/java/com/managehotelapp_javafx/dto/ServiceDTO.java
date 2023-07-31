package com.managehotelapp_javafx.dto;

import com.managehotelapp_javafx.entity.BookingServiceEntity;

import javax.persistence.*;
import java.util.Set;

public class ServiceDTO {

    private int id;

    private int quantity;

    private double price;

    private String description;

    public ServiceDTO(int id, int quantity, double price, String description) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
