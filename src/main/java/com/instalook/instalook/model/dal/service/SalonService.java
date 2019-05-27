package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.entity.Salon;
import java.util.List;

/**
 *
 * @author Ahmed moatasem
 */
public interface SalonService {

    public abstract List<Salon> getAllSalons();
    
    public abstract List<Salon> getAllSalonsById(int SalonId);
}