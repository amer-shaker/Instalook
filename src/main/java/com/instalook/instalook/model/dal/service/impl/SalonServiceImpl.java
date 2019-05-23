/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.service.impl;

import com.instalook.instalook.model.dal.dao.SalonDAO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.service.SalonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ahmed moatasem
 */
@Service
@Transactional
public class SalonServiceImpl implements SalonService {

    @Autowired
    private SalonDAO salonDAO;

    @Override
    public List<Salon> getAllSalons() {

        return salonDAO.getAllSalons();
    }

//    @Override
//    public List<Salon> getAllSallonsByCategory(String salonType) {
//        
//        return salonDAO.getAllSallonsByCategory(salonType);
//    }

}
