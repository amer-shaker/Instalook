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
 * @author Aya
 */
@Service
@Transactional
public class BarberServiceImpl implements BarberService {

    @Autowired
    private BarberDAO barberDAO;

    @Override
    public List<Barber> getAllBarbers(Integer salonId) {
        return barberDAO.getAllBarbers(salonId);
    }

    @Override
    public Barber getBarberById(Integer id) {
        return barberDAO.getBarberById(id);
    }

    @Override
    public int addBarber(BarberDTO barber) {
        return barberDAO.addBarber(barber);
    }

    @Override
    public void updateBarberData(Barber barber) {
        barberDAO.updateBarberData(barber);
    }

    @Override
    public void updateBarberAvailability(Integer barberId, Integer availability) {
        barberDAO.updateBarberAvailability(barberId, availability);
    }

    @Override
    public void rateBarber(Integer barberId, Integer rate) {
        barberDAO.rateBarber(barberId, rate);
    }

    @Override
    public int deleteBarber(Integer barberId) {
        return barberDAO.deleteBarber(barberId);
    }
}
