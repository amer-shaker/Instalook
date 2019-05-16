/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.service.impl;

import com.instalook.instalook.model.dal.dao.ServicesDAO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import com.instalook.instalook.model.dal.service.SalonServicesService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mohamed Ramadan
 */
@org.springframework.stereotype.Service
@Transactional
public class SalonServicesServiceImpl implements SalonServicesService{
    @Autowired
    private ServicesDAO userDAO;

    @Override
    public  List<Service> getAllServicesOfSalon(int SalonId) {
       return userDAO.getAllServicesOfSalon(SalonId);
    }

    @Override
    public int insertServiceToSalon(int salonId, Service salonService) {
       return userDAO.insertServiceToSalon(salonId, salonService);
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
