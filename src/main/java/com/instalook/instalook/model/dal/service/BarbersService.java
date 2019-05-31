package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.entity.Barber;
import java.util.List;

/**
 *
 * @author Aya
 */
public interface BarbersService {

    public abstract List<Barber> getAllBarbers(Integer salonId);

    public abstract Barber getBarberById(Integer id);

    public abstract int addBarber(Barber barber);

    public abstract void updateBarberData(Barber barber);

    public abstract void updateBarberAvailability(Integer barberId, Integer availability);

    public abstract void rateBarber(Integer barberId, Integer rate);

    public abstract int deleteBarber(Integer barberId);
}