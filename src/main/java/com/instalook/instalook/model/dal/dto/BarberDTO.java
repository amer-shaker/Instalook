/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.dto;

import com.instalook.instalook.model.dal.entity.Barber;
import com.instalook.instalook.model.dal.entity.Service;
import java.io.Serializable;

/**
 *
 * @author Aya
 */
public class BarberDTO implements Serializable {

    private Integer salonId;
    private Barber barber;

    public Integer getSalonId() {
        return salonId;
    }

    public void setSalonId(Integer salonId) {
        this.salonId = salonId;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }
}
