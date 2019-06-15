package com.instalook.instalook.model.dal.service.impl;

import com.instalook.instalook.model.dal.dao.ServicesDAO;
import com.instalook.instalook.model.dal.dto.ServiceDTO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import com.instalook.instalook.model.dal.service.SalonServicesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mohamed Ramadan
 */
@org.springframework.stereotype.Service
@Transactional
public class SalonServicesServiceImpl implements SalonServicesService {

    @Autowired
    private ServicesDAO userDAO;

    @Override
    public List<Service> getAllServicesOfSalon(int SalonId) {
        return userDAO.getAllServicesOfSalon(SalonId);
    }

    @Override
    public int insertServiceToSalon(ServiceDTO salonService) {
        return userDAO.insertServiceToSalon(salonService);
    }

    @Override
    public int updateSalonService(Service salonService) {
        return userDAO.updateSalonService(salonService);
    }

    @Override
    public List<Salon> getAllSalonProvideService(String serviceName) {
        return userDAO.getAllSalonProvideService(serviceName);

    }

    @Override
    public int deletServiceFromSalon(int serviceId) {
        return userDAO.deletServiceFromSalon(serviceId);
    }
}
