package com.instalook.instalook.model.dal.dao;

import java.util.List;
import com.instalook.instalook.model.dal.entity.Salon;

/**
 *
 * @author Amer Shaker
 * @author Ahmed moatasem
 */
public interface SalonDAO {

    public abstract Salon login(String email, String password);

    public abstract int register(Salon salon);

    public abstract Salon getSalonById(int salonId);

    public abstract List<Salon> getAllSalons();

    public abstract int getSalonRateById(int salonId);

    public abstract int deleteSalonById(int salonId);
}
