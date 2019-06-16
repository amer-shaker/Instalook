package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.entity.Salon;
import java.util.List;

/**
 *
 * @author Ahmed moatasem
 * @author Amer Shaker
 */
public interface SalonService {

    public abstract Salon login(String email, String password);

    public abstract int register(Salon salon);

    public abstract Salon getSalonById(int salonId);

    public abstract List<Salon> getAllSalons();
}
