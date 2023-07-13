package com.managehotelapp_javafx.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "invoice")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "invoice")
    private Set<BookingRoomEntity> bookingRoomEntities;

    @Column(name = "checkout_late", columnDefinition = "boolean default false")
    private boolean checkoutLate;

    @Column(name = "payment_amount")
    private int paymentAmount;

    @Column(name = "payment_date")
    private Timestamp paymentDate;

    @ManyToOne
    @JoinColumn(name = "invoice_status_id")
    private InvoiceStatusEntity invoiceStatus;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public Set<BookingRoomEntity> getBookingRoomEntities() {
        return bookingRoomEntities;
    }

    public void setBookingRoomEntities(Set<BookingRoomEntity> bookingRoomEntities) {
        this.bookingRoomEntities = bookingRoomEntities;
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

    public InvoiceStatusEntity getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatusEntity invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
