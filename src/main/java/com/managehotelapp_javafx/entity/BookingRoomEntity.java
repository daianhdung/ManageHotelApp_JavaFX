package com.managehotelapp_javafx.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "booking_room")
public class BookingRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private InvoiceEntity invoice;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "status_booking_id")
    private StatusBookingEntity statusBooking;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "bookingRoom")
    private Set<BookingServiceEntity> bookingServiceEntities;

    @Column(name = "checkout_date")
    private Timestamp checkoutDate;

    @Column(name = "payment", columnDefinition = "integer default 0")
    private int payment;

    public StatusBookingEntity getStatusBooking() {
        return statusBooking;
    }

    public void setStatusBooking(StatusBookingEntity statusBooking) {
        this.statusBooking = statusBooking;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceEntity invoice) {
        this.invoice = invoice;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public Set<BookingServiceEntity> getBookingServiceEntities() {
        return bookingServiceEntities;
    }

    public void setBookingServiceEntities(Set<BookingServiceEntity> bookingServiceEntities) {
        this.bookingServiceEntities = bookingServiceEntities;
    }

    public Timestamp getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Timestamp checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
}
