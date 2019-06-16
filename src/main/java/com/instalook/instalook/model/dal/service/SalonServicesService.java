package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.dto.ServiceDTO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import java.util.List;

/**
 *
 * @author Mohamed Ramadan
 */
public interface SalonServicesService {

    public abstract List<Service> getAllServicesOfSalon(int SalonId);

    public abstract int insertServiceToSalon(ServiceDTO salonService);

    public abstract int updateSalonService(Service salonService);

    public abstract List<Salon> getAllSalonProvideService(String serviceName);

    public abstract int deletServiceFromSalon(int serviceId);
}