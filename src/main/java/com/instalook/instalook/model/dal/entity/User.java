package com.instalook.instalook.model.dal.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Amer Shaker
 */
@Entity
@Table(name = "users",
        catalog = "heroku_858654a6d05adcb",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class User implements java.io.Serializable, Cloneable {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String location;
    private byte[] profilePicture;
    private Set<Booking> bookings = new HashSet<>(0);
    private Set<Post> posts = new HashSet<>(0);
    private Set<Salon> salons = new HashSet<>(0);
    private Set<Post> postses_1 = new HashSet<>(0);
    private Set<Point> points = new HashSet<>(0);

    public User() {
    }

    public User(Integer userId, String firstName, String lastName, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(Integer userId, String firstName, String lastName, String email, String password, String location, byte[] profilePicture, Set<Booking> bookings, Set<Post> posts, Set<Salon> salons, Set<Post> postses_1, Set<Point> points) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.location = location;
        this.profilePicture = profilePicture;
        this.bookings = bookings;
        this.posts = posts;
        this.salons = salons;
        this.postses_1 = postses_1;
        this.points = points;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @Column(name = "email", unique = true, nullable = false, length = 100)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "location", length = 100)
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "profile_picture")
    public byte[] getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Booking> getBookings() {
        return this.bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_save_post", catalog = "heroku_858654a6d05adcb",
            joinColumns = {
                @JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "post_id", nullable = false, updatable = false)})
    public Set<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_follow_salon", catalog = "heroku_858654a6d05adcb",
            joinColumns = {
                @JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "salon_id", nullable = false, updatable = false)})
    public Set<Salon> getSalons() {
        return this.salons;
    }

    public void setSalons(Set<Salon> salons) {
        this.salons = salons;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_like_post", catalog = "heroku_858654a6d05adcb",
            joinColumns = {
                @JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "post_id", nullable = false, updatable = false)})
    public Set<Post> getPostses_1() {
        return this.postses_1;
    }

    public void setPostses_1(Set<Post> postses_1) {
        this.postses_1 = postses_1;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Point> getPoints() {
        return this.points;
    }

    public void setPoints(Set<Point> points) {
        this.points = points;
    }
}
