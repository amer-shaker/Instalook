package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.dto.BarberDTO;
import com.instalook.instalook.model.dal.entity.Barber;
import java.util.List;

/**
 *
 * @author Aya
 */
public interface BarberService {

    public abstract int addBarber(BarberDTO barber);
    
    public abstract List<Barber> getAllBarbers(Integer salonId);

    public abstract Barber getBarberById(Integer id);

    public abstract void updateBarberData(Barber barber);

    public abstract void updateBarberAvailability(Integer barberId, Integer availability);

    public abstract void rateBarber(Integer barberId, Integer rate);

    public abstract int deleteBarber(Integer barberId);
}