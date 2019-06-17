package com.instalook.instalook.model.dal.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author abdullah
 */
public class BookingDTO implements Serializable {

    private int userId;
    private int barberId;
    private Date date;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBarberId() {
        return barberId;
    }

    public void setBarberId(int barberId) {
        this.barberId = barberId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
