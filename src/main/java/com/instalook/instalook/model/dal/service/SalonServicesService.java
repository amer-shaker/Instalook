package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import java.util.List;

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
