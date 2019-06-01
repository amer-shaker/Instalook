package com.instalook.instalook.model.dal.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
        catalog = "heroku_858654a6d05adcb"
)
public class Booking implements java.io.Serializable, Cloneable {

    private Integer bookingId;
    private Barber barbers;
    private User user;
    private Date bookingDateTime;

    public Booking() {
    }

    public Booking(Integer bookingId, Barber barbers, User user, Date bookingDateTime) {
        this.bookingId = bookingId;
        this.barbers = barbers;
        this.user = user;
        this.bookingDateTime = bookingDateTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", unique = true, nullable = false)
    public Integer getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(Integer bookingId) {
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
