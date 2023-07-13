package com.managehotelapp_javafx.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CustomerTypeEntity customerType;

    @OneToMany(mappedBy = "customer")
    private Set<BookingEntity> bookingEntities;

    @OneToMany(mappedBy = "customer")
    private Set<InvoiceEntity> invoiceEntities;

    @Column(name = "full_name", length =30, nullable = false)
    private String fullName;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "gender", length =30)
    private String gender;

    @Column(name = "phone", length =11)
    private String phone;

    @Column(name = "identity", length = 12)
    private String identity;

    @Column(name = "passport_no")
    private String passportNo;

    @Column(name = "address", length =50)
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "booking_count")
    private String bookingCount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerTypeEntity getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypeEntity customerType) {
        this.customerType = customerType;
    }

    public Set<BookingEntity> getBookingEntities() {
        return bookingEntities;
    }

    public void setBookingEntities(Set<BookingEntity> bookingEntities) {
        this.bookingEntities = bookingEntities;
    }

    public Set<InvoiceEntity> getInvoiceEntities() {
        return invoiceEntities;
    }

    public void setInvoiceEntities(Set<InvoiceEntity> invoiceEntities) {
        this.invoiceEntities = invoiceEntities;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getBookingCount() {
        return bookingCount;
    }

    public void setBookingCount(String bookingCount) {
        this.bookingCount = bookingCount;
    }
}
