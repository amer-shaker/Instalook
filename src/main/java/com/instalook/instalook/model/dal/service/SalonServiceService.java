package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.dto.ServiceDTO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import java.util.List;

/**
 *
 * @author Amer Shaker
 */
public interface SalonServiceService {

    public abstract int addService(ServiceDTO service);

    public abstract List<Salon> getAllServiceProviders(String serviceName);

    public abstract List<Service> getAllServicesById(int salonId);

    public abstract void updateService(Service service);

    public abstract boolean deleteServiceById(int serviceId);
}
