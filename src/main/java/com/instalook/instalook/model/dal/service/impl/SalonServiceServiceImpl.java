package com.instalook.instalook.model.dal.service.impl;

import com.instalook.instalook.model.dal.dto.ServiceDTO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.instalook.instalook.model.dal.dao.ServiceDAO;
import com.instalook.instalook.model.dal.service.SalonServiceService;

/**
 *
 * @author Mohamed Ramadan
 * @author Amer Shaker
 */
@org.springframework.stereotype.Service
@Transactional
public class SalonServiceServiceImpl implements SalonServiceService {

    @Autowired
    private ServiceDAO serviceDAO;

    @Override
    public List<Service> getAllServices(Integer salonId) {
        return serviceDAO.getAllServices(salonId);
    }

    @Override
    public int insertServiceToSalon(ServiceDTO salonService) {
        return serviceDAO.insertServiceToSalon(salonService);
    }

    @Override
    public int updateSalonService(Service salonService) {
        return serviceDAO.updateSalonService(salonService);
    }

    @Override
    public List<Salon> getAllSalonProvideService(String serviceName) {
        return serviceDAO.getAllSalonProvideService(serviceName);
    }

    @Override
    public int deletServiceFromSalon(int serviceId) {
        return serviceDAO.deletServiceFromSalon(serviceId);
    }
}
