package com.instalook.instalook.model.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Amer Shaker
 */
@Entity
@Table(name = "points",
        catalog = "heroku_858654a6d05adcb"
)
public class Point implements java.io.Serializable {

    private int pointId;
    private Salon salon;
    private User user;
    private Integer pointsNumber;

    public Point() {
    }

    public Point(int pointId) {
        this.pointId = pointId;
    }

    public Point(int pointId, Salon salon, User user, Integer pointsNumber) {
        this.pointId = pointId;
        this.salon = salon;
        this.user = user;
        this.pointsNumber = pointsNumber;
    }

    @Id
    @Column(name = "point_id", unique = true, nullable = false)
    public int getPointId() {
        return this.pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALON_ID")
    public Salon getSalon() {
        return this.salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "points_number")
    public Integer getPointsNumber() {
        return this.pointsNumber;
    }

    public void setPointsNumber(Integer pointsNumber) {
        this.pointsNumber = pointsNumber;
    }

}
