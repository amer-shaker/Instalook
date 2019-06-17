package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.dto.ServiceDTO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import java.util.List;

/**
 *
 * @author Mohamed Ramadan
 * @author Amer Shaker
 */
public interface SalonServiceService {

    public abstract List<Service> getAllServices(Integer salonId);

    public abstract int insertServiceToSalon(ServiceDTO salonService);

    public abstract int updateSalonService(Service salonService);

    public abstract List<Salon> getAllSalonProvideService(String serviceName);

    public abstract int deletServiceFromSalon(int serviceId);
}
