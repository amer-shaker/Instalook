package com.instalook.instalook.model.dal.dto;

import com.instalook.instalook.model.dal.entity.Service;
import java.io.Serializable;

/**
 *
 * @author abdullah
 */
public class ServiceDTO implements Serializable {

    private Integer salonId;
    private Service service;

    public Integer getSalonId() {
        return salonId;
    }

    public void setSalonId(Integer salonId) {
        this.salonId = salonId;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

}
