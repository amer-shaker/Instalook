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
 * @author Amer Shaker
 */
@Service
@Transactional
public class SalonServiceImpl implements SalonService {

    @Autowired
    private SalonDAO salonDAO;

    @Override
    public Salon login(String email, String password) {
        return salonDAO.login(email, password);
    }

    @Override
    public int register(Salon salon) {
        return salonDAO.register(salon);
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonDAO.getAllSalons();
    }

    @Override
    public Salon getSalonById(int salonId) {
        return salonDAO.getSalonById(salonId);
    }
}
