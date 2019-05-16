/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Mohamed Ramadan
 */
public interface SalonServicesService {

    List<Service> getAllServicesOfSalon(int SalonId);

    int insertServiceToSalon(int salonId, Service salonService);

    int updateSalonService(Service salonService);

    List<Salon> getAllSalonProvideService(String serviceName);

    int deletServiceFromSalon(int serviceId);

}
