package com.instalook.instalook.model.dal.service.impl;

import com.instalook.instalook.model.dal.dto.BarberDTO;
import com.instalook.instalook.model.dal.entity.Barber;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.instalook.instalook.model.dal.dao.BarberDAO;
import com.instalook.instalook.model.dal.service.BarberService;

/**
 *
 * @author Aya Wageeh
 */
@Service
@Transactional
public class BarberServiceImpl implements BarberService {

    @Autowired
    private BarberDAO barberDAO;

    @Override
    @Transactional
    public int addBarber(BarberDTO barber) {
        return barberDAO.addBarber(barber);
    }

    @Override
    @Transactional
    public Barber getBarberById(int barberId) {
        return barberDAO.getBarberById(barberId);
    }

    @Override
    @Transactional
    public List<Barber> getAllBarbers(int salonId) {
        return barberDAO.getAllBarbers(salonId);
    }

    @Override
    @Transactional
    public void updateBarberData(Barber barber) {
        barberDAO.updateBarberData(barber);
    }

    @Override
    @Transactional
    public void updateBarberAvailability(int barberId, int availability) {
        barberDAO.updateBarberAvailability(barberId, availability);
    }

    @Override
    @Transactional
    public void rateBarber(int barberId, int rate, int salonId) {
        barberDAO.rateBarber(barberId, rate, salonId);
    }

    @Override
    @Transactional
    public boolean deleteBarberById(int barberId) {
        return barberDAO.deleteBarberById(barberId);
    }
}
