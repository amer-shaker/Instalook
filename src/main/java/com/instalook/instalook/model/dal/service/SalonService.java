package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.entity.Salon;
import java.util.List;

/**
 *
 * @author Amer Shaker
 * @author Ahmed moatasem
 */
public interface SalonService {

    public abstract Salon login(String email, String password);

    public abstract int register(Salon salon);

    public abstract Salon getSalonById(int salonId);

    public abstract List<Salon> getAllSalons();

    public abstract int getSalonRateById(int salonId);

    public abstract int deleteSalonById(int salonId);
}
