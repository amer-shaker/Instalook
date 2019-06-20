package com.instalook.instalook.model.dal.dao;

import com.instalook.instalook.model.dal.dto.ServiceDTO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import java.util.List;

/**
 *
 * @author Amer Shaker
 */
public interface ServiceDAO {

    public abstract List<Service> getAllServices(Integer salonId);

    public abstract List<Salon> getAllSalonProvideService(String serviceName);

    public abstract int insertServiceToSalon(ServiceDTO salonService);

    public abstract void updateService(Service service);

    public abstract int deleteServiceById(int serviceId);
}
