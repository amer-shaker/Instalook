package com.instalook.instalook.model.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Amer Shaker
 */
@Entity
@Table(name = "bookings",
        catalog = "instalook"
)
public class Booking implements java.io.Serializable {

    private int bookingId;
    @JsonIgnore
    private Barber barbers;
    private User user;
    private Date bookingDateTime;

    public Booking() {
    }

    public Booking(int bookingId, Barber barbers, User user, Date bookingDateTime) {
        this.bookingId = bookingId;
        this.barbers = barbers;
        this.user = user;
        this.bookingDateTime = bookingDateTime;
    }

    @Id
    @Column(name = "booking_id", unique = true, nullable = false)
    public int getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barber_id", nullable = false)
    public Barber getBarbers() {
        return this.barbers;
    }

    public void setBarbers(Barber barbers) {
        this.barbers = barbers;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "booking_date_time", nullable = false, length = 19)
    public Date getBookingDateTime() {
        return this.bookingDateTime;
    }

    public void setBookingDateTime(Date bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

}
