package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.entity.Barber;
import java.util.List;

/**
 *
 * @author Aya
 */
public interface BarbersService {

    public List<Barber> getAllBarbers(Integer salonId);

    public Barber getBarberById(Integer id);

    public int addBarber(Barber barber);

    public void updateBarberData(Barber barber);

    public void updateBarberAvailability(Integer barberId, Integer availability);

    public void rateBarber(Integer barberId, Integer rate);

    public int deleteBarber(Integer barberId);
}
