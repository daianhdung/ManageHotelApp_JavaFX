package com.managehotelapp_javafx.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column(name = "booking_type")
    private String bookingType;

    @CreationTimestamp
    @Column(name = "booking_date")
    private Timestamp bookingDate;

    @Column(name = "estimate_date_in")
    private Timestamp estimateDateIn;

    @Column(name = "estimate_date_out")
    private Timestamp estimateDateOut;

    @Column(name = "actual_date_in")
    private Timestamp actualDateIn;

    @Column(name = "actual_date_out")
    private Timestamp actualDateOut;

    @Column(name = "num_adult")
    private int numAdult;

    @Column(name = "num_children")
    private int numChildren;

    @Column(name = "deposit")
    private int deposit;

    @Column(name = "special_request", length = 1000)
    private String specialRequest;

    @ManyToOne
    @JoinColumn(name = "status_booking_id")
    private StatusBookingEntity statusBooking;


    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
    private Set<BookingRoomEntity> bookingRoomEntities;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT now()")
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Timestamp getEstimateDateIn() {
        return estimateDateIn;
    }

    public void setEstimateDateIn(Timestamp estimateDateIn) {
        this.estimateDateIn = estimateDateIn;
    }

    public Timestamp getEstimateDateOut() {
        return estimateDateOut;
    }

    public void setEstimateDateOut(Timestamp estimateDateOut) {
        this.estimateDateOut = estimateDateOut;
    }

    public Timestamp getActualDateIn() {
        return actualDateIn;
    }

    public void setActualDateIn(Timestamp actualDateIn) {
        this.actualDateIn = actualDateIn;
    }

    public Timestamp getActualDateOut() {
        return actualDateOut;
    }

    public void setActualDateOut(Timestamp actualDateOut) {
        this.actualDateOut = actualDateOut;
    }

    public int getNumAdult() {
        return numAdult;
    }

    public void setNumAdult(int numAdult) {
        this.numAdult = numAdult;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public StatusBookingEntity getStatusBooking() {
        return statusBooking;
    }

    public void setStatusBooking(StatusBookingEntity statusBooking) {
        this.statusBooking = statusBooking;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Set<BookingRoomEntity> getBookingRoomEntities() {
        return bookingRoomEntities;
    }

    public void setBookingRoomEntities(Set<BookingRoomEntity> bookingRoomEntities) {
        this.bookingRoomEntities = bookingRoomEntities;
    }

    public BookingDTO getDTO()
    {
        
    }
}
