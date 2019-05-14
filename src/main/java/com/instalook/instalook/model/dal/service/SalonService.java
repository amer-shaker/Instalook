/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.entity.Salon;
import java.util.List;

/**
 *
 * @author Ahmed moatasem
 */
public interface SalonService {
    
    public abstract List<Salon> getAllSalons();
    
}
