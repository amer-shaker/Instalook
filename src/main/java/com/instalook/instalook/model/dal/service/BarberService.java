package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.dto.BarberDTO;
import com.instalook.instalook.model.dal.entity.Barber;
import java.util.List;

/**
 *
 * @author Aya Wageeh
 */
public interface BarberService {

    public abstract int addBarber(BarberDTO barber);

    public abstract Barber getBarberById(int barberId);

    public abstract List<Barber> getAllBarbers(int salonId);

    public abstract void updateBarberData(Barber barber);

    public abstract void updateBarberAvailability(int barberId, int availability);

    public abstract void rateBarber(int barberId, int rate, int salonId);

    public abstract boolean deleteBarberById(int barberId);
}
