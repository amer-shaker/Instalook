/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author abdullah
 */
public class BookingDTO implements Serializable {

    int userId;
    int barberId;
    Date date;

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
