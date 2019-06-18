package com.instalook.instalook.model.dal.dto;

import com.instalook.instalook.model.dal.entity.Barber;
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
