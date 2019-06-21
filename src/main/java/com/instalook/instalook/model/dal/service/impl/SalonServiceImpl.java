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
 * @author Amer Shaker
 */
@Service
@Transactional
public class SalonServiceImpl implements SalonService {

    @Autowired
    private SalonDAO salonDAO;

    @Override
    @Transactional
    public int register(Salon salon) {
        return salonDAO.register(salon);
    }

    @Override
    @Transactional
    public Salon login(String email, String password) {
        return salonDAO.login(email, password);
    }

    @Override
    @Transactional
    public Salon getSalonById(int salonId) {
        return salonDAO.getSalonById(salonId);
    }

    @Override
    @Transactional
    public double getSalonRateById(int salonId) {
        return salonDAO.getSalonRateById(salonId);
    }

    @Override
    @Transactional
    public List<Salon> getAllSalons() {
        return salonDAO.getAllSalons();
    }

    @Override
    @Transactional
    public boolean deleteSalonById(int salonId) {
        return salonDAO.deleteSalonById(salonId);
    }
}
