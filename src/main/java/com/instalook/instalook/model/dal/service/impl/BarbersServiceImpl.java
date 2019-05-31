package com.instalook.instalook.model.dal.service.impl;

import com.instalook.instalook.model.dal.dao.BarbersDAO;
import com.instalook.instalook.model.dal.entity.Barber;
import com.instalook.instalook.model.dal.service.BarbersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aya
 */
@Service
@Transactional
public class BarbersServiceImpl implements BarbersService {

    @Autowired
    private BarbersDAO barbersDAO;

    @Override
    public List<Barber> getAllBarbers(Integer salonId) {
        return barbersDAO.getAllBarbers(salonId);
    }

    @Override
    public Barber getBarberById(Integer id) {
        return barbersDAO.getBarberById(id);
    }

    @Override
    public int addBarber(Barber barber) {
        return barbersDAO.addBarber(barber);
    }

    @Override
    public void updateBarberData(Barber barber) {
        barbersDAO.updateBarberData(barber);
    }

    @Override
    public void updateBarberAvailability(Integer barberId, Integer availability) {
        barbersDAO.updateBarberAvailability(barberId, availability);
    }

    @Override
    public void rateBarber(Integer barberId, Integer rate) {
        barbersDAO.rateBarber(barberId, rate);
    }

    @Override
    public int deleteBarber(Integer barberId) {
        return barbersDAO.deleteBarber(barberId);
    }

}
