package com.instalook.instalook.model.dal.dao;

import java.util.List;
import com.instalook.instalook.model.dal.entity.Salon;

/**
 *
 * @author Ahmed moatasem
 * @author Amer Shaker
 */
public interface SalonDAO {

    public abstract int addSalon(Salon salon);

    public abstract List<Salon> getAllSalons();

    public abstract List<Salon> getSalonById(int SalonId);
}