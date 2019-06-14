package com.instalook.instalook.model.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "salons",
        catalog = "heroku_858654a6d05adcb",
        uniqueConstraints = @UniqueConstraint(columnNames = "salon_email")
)
public class Salon implements java.io.Serializable {

    private Integer salonId;
    private String salonName;
    private String salonEmail;
    private String salonPassword;
    private String salonLocation;
    private String salonType;
    private Set<Barber> barbers = new HashSet<>(0);
    private Set<Image> images = new HashSet<>(0);
    private Set<Post> posts = new HashSet<>(0);
    private Set<Service> services = new HashSet<>(0);
    private Set<Point> points = new HashSet<>(0);
    private Set<User> users = new HashSet<>(0);

    public Salon() {
    }

    public Salon(Integer salonId, String salonName, String salonEmail, String salonPassword, String salonLocation, String salonType) {
        this.salonId = salonId;
        this.salonName = salonName;
        this.salonEmail = salonEmail;
        this.salonPassword = salonPassword;
        this.salonLocation = salonLocation;
        this.salonType = salonType;
    }

    public Salon(Integer salonId, String salonName, String salonEmail, String salonPassword, String salonLocation, String salonType, Set<Barber> barbers, Set<Image> images, Set<Post> posts, Set<Service> services, Set<Point> points, Set<User> users) {
        this.salonId = salonId;
        this.salonName = salonName;
        this.salonEmail = salonEmail;
        this.salonPassword = salonPassword;
        this.salonLocation = salonLocation;
        this.salonType = salonType;
        this.barbers = barbers;
        this.images = images;
        this.posts = posts;
        this.services = services;
        this.points = points;
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salon_id", unique = true, nullable = false)
    public Integer getSalonId() {
        return this.salonId;
    }

    public void setSalonId(Integer salonId) {
        this.salonId = salonId;
    }

    @Column(name = "salon_name", nullable = false, length = 100)
    public String getSalonName() {
        return this.salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    @Column(name = "salon_email", unique = true, nullable = false, length = 100)
    public String getSalonEmail() {
        return salonEmail;
    }

    public void setSalonEmail(String salonEmail) {
        this.salonEmail = salonEmail;
    }

    @Column(name = "salon_password", nullable = false, length = 100)
    public String getSalonPassword() {
        return salonPassword;
    }

    public void setSalonPassword(String salonPassword) {
        this.salonPassword = salonPassword;
    }

    @Column(name = "salon_location", nullable = false, length = 100)
    public String getSalonLocation() {
        return salonLocation;
    }

    public void setSalonLocation(String salonLocation) {
        this.salonLocation = salonLocation;
    }

    @Column(name = "salon_type", nullable = false, length = 45)
    public String getSalonType() {
        return this.salonType;
    }

    public void setSalonType(String salonType) {
        this.salonType = salonType;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "salon")
    @JsonIgnore
    public Set<Barber> getBarbers() {
        return this.barbers;
    }

    public void setBarbers(Set<Barber> barbers) {
        this.barbers = barbers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "salon")
    public Set<Image> getImages() {
        return this.images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "salon")
    public Set<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "salon_provide_services", catalog = "heroku_858654a6d05adcb",
            joinColumns = {
                @JoinColumn(name = "salon_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "service_id", nullable = false, updatable = false)})

    public Set<Service> getServices() {
        return this.services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "salon")
    public Set<Point> getPoints() {
        return this.points;
    }

    public void setPoints(Set<Point> points) {
        this.points = points;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "salons")
    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "[\"salonName:\"" + salonName + "]";
    }
}
