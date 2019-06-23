package com.instalook.instalook.model.dal.dao;

import com.instalook.instalook.model.dal.dto.BarberDTO;
import com.instalook.instalook.model.dal.entity.Barber;
import java.util.List;

/**
 *
 * @author Aya Wageeh
 */
public interface BarberDAO {

    public static final String BARBERS_TABLE = "instalook.barbers";
    public static final String BARBER_ID_COLUMN = "barber_id";
    public static final String FIRST_NAME_COLUMN = "first_name";
    public static final String LAST_NAME_COLUMN = "last_name";
    public static final String ROLE_COLUMN = "role";
    public static final String RATE_COLUMN = "rate";
    public static final String BARBER_PICTURE_COLUMN = "barber_picture";
    public static final String IS_AVAILABLE_COLUMN = "is_available";
    public static final String SALON_ID_COLUMN = "salon_id";

    public abstract int addBarber(BarberDTO barber);

    public abstract Barber getBarberById(int barberId);

    public abstract List<Barber> getAllBarbers(int salonId);

    public abstract void updateBarberData(Barber barber);

    public abstract void updateBarberAvailability(int barberId, int availability);

    public abstract void rateBarber(int barberId, int rate, int salonId);

    public abstract boolean deleteBarberById(int barberId);
}
