/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.service;


import com.instalook.instalook.model.dal.entity.Barber;
import java.util.List;

/**
 *
 * @author abdullah
 */
public interface BookingService {

    /**
     *
     * @param barber
     * @return
     */
    public abstract List<Object[]> getBookings(Barber barber);
}