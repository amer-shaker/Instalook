package com.instalook.instalook.model.dal.dao;

import java.util.List;
import com.instalook.instalook.model.dal.entity.Salon;

/**
 *
 * @author Amer Shaker
 */
public interface SalonDAO {

    public abstract int register(Salon salon);

    public abstract Salon login(String email, String password);

    public abstract Salon getSalonById(int salonId);

    public abstract double getSalonRateById(int salonId);

    public abstract List<Salon> getAllSalons();

    public abstract boolean deleteSalonById(int salonId);
}
