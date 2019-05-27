package com.instalook.instalook.model.dal.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Amer Shaker
 */
@Entity
@Table(name = "posts",
        catalog = "heroku_858654a6d05adcb"
)
public class Post implements java.io.Serializable {

    private int postId;
    private Salon salon;
    private String postText;
    private byte[] postImage;
    private int likesNumber;
    private Set<User> savedPostUsers = new HashSet<>(0);
    private Set<User> likedPostUsers = new HashSet<>(0);

    public Post() {
    }

    public Post(int postId, int likesNumber) {
        this.postId = postId;
        this.likesNumber = likesNumber;
    }

    public Post(int postId, Salon salons, String postText, byte[] postImage, int likesNumber, Set<User> savedPostUsers, Set<User> likedPostUsers) {
        this.postId = postId;
        this.salon = salons;
        this.postText = postText;
        this.postImage = postImage;
        this.likesNumber = likesNumber;
        this.savedPostUsers = savedPostUsers;
        this.likedPostUsers = likedPostUsers;
    }

    @Id
    @Column(name = "post_id", unique = true, nullable = false)
    public int getPostId() {
        return this.postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salon_id")
    public Salon getSalon() {
        return this.salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    @Column(name = "post_text", length = 500)
    public String getPostText() {
        return this.postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    @Column(name = "post_image")
    public byte[] getPostImage() {
        return this.postImage;
    }

    public void setPostImage(byte[] postImage) {
        this.postImage = postImage;
    }

    @Column(name = "likes_number", nullable = false)
    public int getLikesNumber() {
        return this.likesNumber;
    }

    public void setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "posts")
    public Set<User> getSavedPostUsers() {
        return this.savedPostUsers;
    }

    public void setSavedPostUsers(Set<User> savedPostUsers) {
        this.savedPostUsers = savedPostUsers;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "posts")
    public Set<User> getLikedPostUsers() {
        return this.likedPostUsers;
    }

    public void setLikedPostUsers(Set<User> likedPostUsers) {
        this.likedPostUsers = likedPostUsers;
    }

}
