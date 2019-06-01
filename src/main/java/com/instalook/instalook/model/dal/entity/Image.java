package com.instalook.instalook.model.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Amer Shaker
 */
@Entity
@Table(name = "images",
        catalog = "heroku_858654a6d05adcb"
)
public class Image implements java.io.Serializable {

    private Integer imageId;
    private Salon salon;
    private byte[] image;

    public Image() {
    }

    public Image(Integer imageId) {
        this.imageId = imageId;
    }

    public Image(int imageId, Salon salon, byte[] image) {
        this.imageId = imageId;
        this.salon = salon;
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", unique = true, nullable = false)
    public Integer getImageId() {
        return this.imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salon_id")
    @JsonIgnore
    public Salon getSalon() {
        return this.salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    @Column(name = "image")
    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
