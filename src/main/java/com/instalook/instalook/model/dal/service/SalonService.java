package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.entity.Salon;
import java.util.List;

/**
 *
 * @author Ahmed moatasem
 * @author Amer Shaker
 */
public interface SalonService {

    public abstract int addSalon(Salon salon);

    public abstract List<Salon> getAllSalons();

    public abstract List<Salon> getAllSalonsById(int SalonId);
}