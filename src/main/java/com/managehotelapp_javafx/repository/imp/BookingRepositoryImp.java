package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.BookingEntity;
import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.repository.BookingRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRepositoryImp extends AbstractRepository<BookingEntity> implements BookingRepository {

    @Override
    public List<BookingEntity> findAllBooking() {
        return query("FROM BookingEntity", null);
    }

    @Override
    public BookingEntity findBookingById(int idBooking) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id" , idBooking);
        List<BookingEntity> result = query("FROM BookingEntity WHERE id = :id", parameters);
        return result.isEmpty() ? null : result.get(0);
    }
}
