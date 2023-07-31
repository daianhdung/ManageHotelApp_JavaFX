package com.managehotelapp_javafx.dto;

import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.entity.InvoiceStatusEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class InvoiceDTO {
    private int id;
    private String customer;

    private boolean checkoutLate;
    private int paymentAmount;
    private Timestamp paymentDate;

    private String invoiceStatus;

    private Timestamp createdAt;

    private int idCustomer;

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public boolean isCheckoutLate() {
        return checkoutLate;
    }

    public void setCheckoutLate(boolean checkoutLate) {
        this.checkoutLate = checkoutLate;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
