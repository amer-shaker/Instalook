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
@Table(name = "images",
        catalog = "instalook"
)
public class Image implements java.io.Serializable {

    private int imageId;
    private Salon salon;
    private byte[] image;

    public Image() {
    }

    public Image(int imageId) {
        this.imageId = imageId;
    }

    public Image(int imageId, Salon salon, byte[] image) {
        this.imageId = imageId;
        this.salon = salon;
        this.image = image;
    }

    @Id
    @Column(name = "image_id", unique = true, nullable = false)
    public int getImageId() {
        return this.imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salon_id")
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
