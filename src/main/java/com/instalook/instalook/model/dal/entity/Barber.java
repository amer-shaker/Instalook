package com.instalook.instalook.model.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Amer Shaker
 */
@Entity
@Table(name = "barbers",
        catalog = "heroku_858654a6d05adcb"
)
public class Barber implements java.io.Serializable, Cloneable {

    private Integer barberId;
    @JsonIgnore
    private Salon salon;
    private String firstName;
    private String lastName;
    private String role;
    private Integer rate;
    private byte[] barberPicture;
    private int isAvailable;
    private Set<Booking> bookings = new HashSet<>(0);

    public Barber() {
    }

    public Barber(Integer barberId, String firstName, String lastName, String role, byte[] barberPicture, int isAvailable) {
        this.barberId = barberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.barberPicture = barberPicture;
        this.isAvailable = isAvailable;
    }

    public Barber(Integer barberId, Salon salon, String firstName, String lastName, String role, Integer rate, byte[] barberPicture, int isAvailable, Set<Booking> bookings) {
        this.barberId = barberId;
        this.salon = salon;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.rate = rate;
        this.barberPicture = barberPicture;
        this.isAvailable = isAvailable;
        this.bookings = bookings;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "barber_id", unique = true, nullable = false)
    public Integer getBarberId() {
        return this.barberId;
    }

    public void setBarberId(Integer barberId) {
        this.barberId = barberId;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salon_id")
    public Salon getSalon() {
        return this.salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    @Column(name = "first_name", nullable = false, length = 45)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false, length = 45)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "role", nullable = false, length = 100)
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "rate")
    public Integer getRate() {
        return this.rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Column(name = "barber_picture", nullable = false)
    public byte[] getBarberPicture() {
        return this.barberPicture;
    }

    public void setBarberPicture(byte[] barberPicture) {
        this.barberPicture = barberPicture;
    }

    @Column(name = "is_available", nullable = false)
    public int getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "barbers")
    public Set<Booking> getBookings() {
        return this.bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

}
