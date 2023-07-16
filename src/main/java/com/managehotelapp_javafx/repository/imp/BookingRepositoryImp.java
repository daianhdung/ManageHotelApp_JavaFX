package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.BookingEntity;
import com.managehotelapp_javafx.repository.BookingRepository;

import java.util.List;

public class BookingRepositoryImp extends AbstractRepository<BookingEntity> implements BookingRepository {

    @Override
    public List<BookingEntity> findAllBooking() {
        return query("FROM BookingEntity", null);
    }
}
