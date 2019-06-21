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
 * @author Amer Shaker
 */
@org.springframework.stereotype.Service
@Transactional
public class SalonServiceServiceImpl implements SalonServiceService {

    @Autowired
    private ServiceDAO serviceDAO;

    @Override
    @Transactional
    public int addService(ServiceDTO service) {
        return serviceDAO.addService(service);
    }

    @Override
    @Transactional
    public List<Salon> getAllServiceProviders(String serviceName) {
        return serviceDAO.getAllServiceProviders(serviceName);
    }

    @Override
    @Transactional
    public List<Service> getAllServicesById(int salonId) {
        return serviceDAO.getAllServicesById(salonId);
    }

    @Override
    @Transactional
    public void updateService(Service service) {
        serviceDAO.updateService(service);
    }

    @Override
    @Transactional
    public boolean deleteServiceById(int serviceId) {
        return serviceDAO.deleteServiceById(serviceId);
    }
}
