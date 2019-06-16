/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getSalonId() {
        return salonId;
    }

    public void setSalonId(Integer salonId) {
        this.salonId = salonId;
    }

}
