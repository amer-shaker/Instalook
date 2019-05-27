package com.instalook.instalook.model.dal.dao;

import java.util.List;
import com.instalook.instalook.model.dal.entity.Salon;

/**
 *
 * @author Ahmed moatasem
 */
public interface SalonDAO {

    public abstract List<Salon> getAllSalons();

    public abstract List<Salon> getSalonById(int SalonId);
}